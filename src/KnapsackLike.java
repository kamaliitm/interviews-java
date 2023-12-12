import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Request {
    int cost;
    int count;

    Request(int cost, int count) {
        this.cost = cost;
        this.count = count;
    }
}

public class KnapsackLike {

    int maximumBandwidth;

    public KnapsackLike(int maximumBandwidth) {
        this.maximumBandwidth = maximumBandwidth;
    }

    // V(i, w) = max{ V(i-1, w), v(i) + V(i-1, w-w(i)) }  if w(i) <= w
    //          = V(i-1, w)
    public List<Request> distributeRequests(List<Request> requests) {
        if (requests == null || requests.size() == 0) {
            return requests;
        }

        int numRequests = requests.size();

        int[][] costDPCache = new int[numRequests+1][maximumBandwidth+1];
        for (int j=0; j <= maximumBandwidth; j++) {
            costDPCache[0][j] = 0;
        }

        for (int i=1; i <= numRequests; i++) {
            costDPCache[i][0] = 0;
            for (int j = 1; j <= maximumBandwidth; j++) {
                int withoutThisRequest = costDPCache[i-1][j];

                Request currRequest = requests.get(i-1);
                int withThisRequest = currRequest.cost <= j ?
                        currRequest.cost + costDPCache[i-1][j-currRequest.cost] : 0;

                costDPCache[i][j] = Math.max(withThisRequest, withoutThisRequest);
            }
        }

        List<Request> chosenRequests = new ArrayList<>();

        int i = numRequests;
        int j = maximumBandwidth;
        while(i > 0 && j >= 0) {
            if (costDPCache[i][j] != costDPCache[i-1][j]) {
                chosenRequests.add(requests.get(i-1));
                j -= requests.get(i-1).cost;
            }
            i--;
        }

        return chosenRequests;
    }

    public static void main(String[] args) {
        KnapsackLike knapsackLike = new KnapsackLike(500);
        List<Request> chosenRequests = knapsackLike.distributeRequests(Arrays.asList(
                new Request(200, 142),
                new Request(100, 142),
                new Request(350, 450),
                new Request(50, 124),
                new Request(100, 189)
        ));
        System.out.println(chosenRequests);
    }
}
