import java.util.*;

class EventTime {
    int time;
    boolean isStart;

    public EventTime(int time, boolean isStart) {
        this.time = time;
        this.isStart = isStart;
    }
}

class EventTimeComparator implements Comparator<EventTime> {


    @Override
    public int compare(EventTime o1, EventTime o2) {
        if (o1.time < o2.time) {
            return -1;
        } else if (o1.time > o2.time) {
            return 1;
        }

        if (o1.isStart) {
            return -1;
        } else if (o2.isStart) {
            return 1;
        }

        return 0;
    }
}

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        List<EventTime> eventTimes = new ArrayList<>();
        for (int[] interval: intervals) {
            eventTimes.add(new EventTime(interval[0], true));
            eventTimes.add(new EventTime(interval[1], false));
        }

        Collections.sort(eventTimes, new EventTimeComparator());

        int eventCount = 0;
        List<int[]> mergedIntervalsList = new ArrayList<>();
        int start = 0;
        for (EventTime eventTime: eventTimes) {
            if (eventTime.isStart) {
                if (eventCount == 0) {
                    start = eventTime.time;
                }
                eventCount++;
            } else {
                eventCount--;
                if (eventCount == 0) {
                    mergedIntervalsList.add(new int[]{start, eventTime.time});
                }
            }
        }

        return mergedIntervalsList.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        MergeIntervals mergeIntervals = new MergeIntervals();
        int[][] mergedIntervals = mergeIntervals.merge(new int[][]{
                {74,78},{61,63},{46,50},{51,54},{50,50},{60,64},{39,42},{25,27}
        });
        System.out.println(mergedIntervals);
    }
}
