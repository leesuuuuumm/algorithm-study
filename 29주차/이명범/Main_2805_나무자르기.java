import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int top = Integer.MIN_VALUE;
    static int N, M, tree[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tree = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
            top = Math.max(top, tree[i]);
        }

        System.out.println(binarySearch());
    }

    private static int binarySearch() {
        int l = 0;
        int r = top;

        while (l + 1 < r) {
            int m = (l + r) / 2;

            if (check(m)) l = m;
            else r = m;
        }

        return l;
    }

    private static boolean check(int height) {
        long sum = 0;

        for (int t : tree) {
            sum += Math.max(t - height, 0);
        }

        return sum >= M;
    }
}