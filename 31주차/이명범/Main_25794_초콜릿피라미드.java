import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int N = Math.min(R, C);
            int D = Math.abs(R - C);

            long white = 2 * sumSquare(N) - 2 * sum(N) + N;
            white += D * (2 * sum(N) - N);

            long dark = 2 * sumSquare(N) - 2 * sum(N);
            dark += D * (2 * sum(N) - N);

            sb.append(white).append(" ").append(dark).append("\n");
        }
        System.out.println(sb);
    }

    private static long sum(int n) {
        return n * (n + 1L) / 2;
    }
    private static long sumSquare(int n) {
        return n * (n + 1L) * (2L * n + 1) / 6;
    }
}