public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }

        int numOfCoins = coins.length;
        int[] coinChangeCache = new int[amount+1];
        coinChangeCache[0] = 0;
        for (int i=1; i <= amount; i++) {
            coinChangeCache[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= amount; i++) {
            for (int coin: coins) {
                if (coin <= i) {
                    coinChangeCache[i] = Math.min(coinChangeCache[i],
                            coinChangeCache[i-coin] == Integer.MAX_VALUE ?
                                    Integer.MAX_VALUE : 1 + coinChangeCache[i - coin]);
                }
            }
        }

        if (coinChangeCache[amount] != Integer.MAX_VALUE) {
            return coinChangeCache[amount];
        }

        return -1;
    }

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        int minCoins = coinChange.coinChange(new int[]{2}, 3);
        System.out.println(minCoins);
    }
}
