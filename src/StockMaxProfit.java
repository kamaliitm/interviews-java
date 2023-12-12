public class StockMaxProfit {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int minSoFar = prices[0];
        int maxProfit = 0;

        for (int i=1; i < prices.length; i++) {
            if (prices[i] < minSoFar) {
                minSoFar = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i]-minSoFar);
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        StockMaxProfit stockMaxProfit = new StockMaxProfit();
        int maxProfit = stockMaxProfit.maxProfit(new int[]{7,1,5,3,6,4});
        System.out.println(maxProfit);
    }

}
