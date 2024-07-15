public class Main {
    static int N, S;
    static int[] num;

    public static void main(String[] args) throws Exception {
        N = read(); S = read();
        num = new int[N];

        for (int i = 0; i < N; i++) {
            num[i] = read();
        }

        System.out.println(solve());
    }

    private static int solve() {
        int sum = 0;
        int result = Integer.MAX_VALUE;

        for (int l = 0, r = 0; r < N; r++) {
            sum += num[r];

            if (sum < S) continue;

            while (sum >= S) {
                sum -= num[l++];
            }
            sum += num[--l];

            result = Math.min(result, r - l + 1);
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }
}