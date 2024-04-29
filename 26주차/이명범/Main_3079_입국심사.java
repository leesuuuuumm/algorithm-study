public class Main {
    static int N, M;
    static int[] num;

    public static void main(String[] args) throws Exception {
        N = read(); M = read();
        num = new int[N];

        for (int i = 0; i < N; i++) {
            num[i] = read();
        }

        System.out.println(binarySearch());
    }

    private static long binarySearch() {
        long l = 1;
        long r = 1_000_000_000L * 1_000_000_000L;

        while (l <= r) {
            long m = (l + r) / 2;

            if (!check(m)) l = m + 1;
            else r = m - 1;
        }

        return l;
    }

    private static boolean check(long m) {
        long sum = 0;

        for (int n : num) {
            sum += m / n;
            
            if (sum >= M) return true;
        }

        return sum >= M;
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }
}