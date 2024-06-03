import java.util.Arrays;

public class Main {
    static class Paper implements Comparable<Paper> {
        int r;
        int c;

        public Paper(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Paper o) {
            return r * c - o.r * o.c;
        }
    }

    public static void main(String[] args) throws Exception {
        int N = read();
        Paper[] papers = new Paper[N + 1];
        int[] dp = new int[N + 1];
        papers[0] = new Paper(0, 0);
        for (int i = 1; i <= N; i++) {
            int r = read(), c = read();
            papers[i] = new Paper(r, c);
        }
        Arrays.sort(papers);

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                if ((papers[i].r >= papers[j].r && papers[i].c >= papers[j].c) || (papers[i].c >= papers[j].r && papers[i].r >= papers[j].c)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int result = 0;
        for (int i = 1; i <= N; i++) {
            result = Math.max(result, dp[i]);
        }
        System.out.println(result);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}