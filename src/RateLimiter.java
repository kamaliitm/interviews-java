import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

interface RateLimiterAlgorithm {
    boolean shouldAccept(long timestampInSeconds, String userId, String requestId);
}

class Window {
    private final long startTime;
    private int requestCount;

    Window(long startTime) {
        this.startTime = startTime;
    }

    public void setRequestCount(int requestCount) {
        this.requestCount = requestCount;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public long getStartTime() {
        return startTime;
    }
}

class FixedWindowCounterAlgorithm implements RateLimiterAlgorithm {

    private final int requestLimitPerUser;
    private final int timeWindowInSeconds;
    Map<String, Window> userRequestWindowMap;

    FixedWindowCounterAlgorithm(int timeWindowInSeconds, int requestLimitPerUser) {
        this.timeWindowInSeconds = timeWindowInSeconds;
        this.requestLimitPerUser = requestLimitPerUser;
        this.userRequestWindowMap = new ConcurrentHashMap<>();
    }

    @Override
    public boolean shouldAccept(long timestampInSeconds, String userId, String requestId) {

        Window window = userRequestWindowMap.getOrDefault(userId, new Window(timestampInSeconds));
        if (window.getStartTime() < timestampInSeconds-timeWindowInSeconds) {
            window = new Window(timestampInSeconds);
        }

        if (window.getRequestCount() == requestLimitPerUser) {
            return false;
        }

        window.setRequestCount(window.getRequestCount()+1);

        return true;
    }
}

class SlidingWindowAlgorithm implements RateLimiterAlgorithm {

    int requestLimitPerUser;
    int timeWindowInSeconds;
    Map<String, List<Long>> userRequestTimestamps;

    SlidingWindowAlgorithm(int requestLimitPerUser, int timeWindowInSeconds) {
        this.requestLimitPerUser = requestLimitPerUser;
        this.timeWindowInSeconds = timeWindowInSeconds;
        this.userRequestTimestamps = new ConcurrentHashMap<>();
    }

    @Override
    public boolean shouldAccept(long timestampInSeconds, String userId, String requestId) {

        List<Long> requestTimestamps = this.userRequestTimestamps.getOrDefault(userId, new ArrayList<>());

        requestTimestamps =  requestTimestamps.stream()
                .filter(ts -> timestampInSeconds - ts < timeWindowInSeconds)
                .collect(Collectors.toList());

        if (requestTimestamps.size() < requestLimitPerUser) {
            requestTimestamps.add(timestampInSeconds);
            this.userRequestTimestamps.put(userId, requestTimestamps);
            return true;
        }

        return false;
    }
}

class TimerWheelAlgorithm implements RateLimiterAlgorithm {

    int requestLimitPerUser;
    Map<Integer, Map<String, Integer>> requestCountAtSlots;
    Map<Integer, Long> currentTimeAtSlots;
    int timeoutInSeconds;

    TimerWheelAlgorithm(int timeoutInSeconds, int requestLimitPerUser) {
        this.requestLimitPerUser = requestLimitPerUser;
        this.timeoutInSeconds = timeoutInSeconds;
    }

    @Override
    public boolean shouldAccept(long timestampInSeconds, String userId, String requestId) {
        int slot = (int) timestampInSeconds % timeoutInSeconds;
        if (currentTimeAtSlots.get(slot) != timestampInSeconds) {
            // existing requests timed out; clear the slot
            requestCountAtSlots.put(slot, new ConcurrentHashMap<String, Integer>(){{
                put(userId, 1);
            }});
            currentTimeAtSlots.put(slot, timestampInSeconds);
            return true;
        }

        Map<String, Integer> userRequestCountMap = requestCountAtSlots.getOrDefault(slot, new ConcurrentHashMap<>());
        int currentRequestCountForUser = userRequestCountMap.getOrDefault(userId, 0);
        if (currentRequestCountForUser == requestLimitPerUser) {
            return false;
        }

        userRequestCountMap.put(userId, currentRequestCountForUser+1);
        requestCountAtSlots.put(slot, userRequestCountMap);

        return true;
    }

}

/**
 * Please note that I've implemented the algorithms with in-memory cache. In the actual app, we
 * would use Redis and its methods to update the counters or timestamps. We could use MULTI command
 * to execute multiple commands atomically as a transaction.
 */
public class RateLimiter {

    RateLimiterAlgorithm rateLimiterAlgorithm;

    public RateLimiter(RateLimiterAlgorithm rateLimiterAlgorithm) {
        this.rateLimiterAlgorithm = rateLimiterAlgorithm;
    }

    public boolean onRequest(String userId, String requestId) {
        return rateLimiterAlgorithm.shouldAccept(System.currentTimeMillis()/1000, userId, requestId);
    }

    public static void main(String []args) throws InterruptedException {
        RateLimiter rateLimiter = new RateLimiter(new SlidingWindowAlgorithm(3, 5));
        System.out.println(rateLimiter.onRequest("user1", "req1"));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(rateLimiter.onRequest("user1", "req2"));
        System.out.println(rateLimiter.onRequest("user2", "req3"));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(rateLimiter.onRequest("user1", "req4"));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(rateLimiter.onRequest("user1", "req5")); // should drop
        TimeUnit.SECONDS.sleep(3);
        System.out.println(rateLimiter.onRequest("user1", "req6")); // should accept
    }
}
