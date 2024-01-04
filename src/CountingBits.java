public class CountingBits {

    public int[] countBits(int n) {
        int[] counts = new int[n+1];
        counts[0] = 0;
        for (int i = 1; i <= n; i++) {
            int numOnes = 0;
            int num = i;
            while (num != 0) {
                num = num & (num-1);
                numOnes++;
            }
            counts[i] = numOnes;
        }

        return counts;
    }

    public static void main(String[] args) {
        CountingBits countingBits = new CountingBits();
        int[] counts = countingBits.countBits(2);
        System.out.println(counts);
    }
}
