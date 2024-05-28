import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N;
    static List<Integer>[] elist;
    public static void main(String[] args) throws Exception {
        N = read();
        elist = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            elist[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            int a = read();
            int b = read();
            elist[a].add(b);
            elist[b].add(a);
        }

        int q = read();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < q; i++) {
            int t = read();
            int k = read();

            sb.append(solve(t, k) ? "yes" : "no").append("\n");
        }

        System.out.println(sb);
    }

    private static boolean solve(int t, int k) {
        return t == 1 ? isCutVertex(k) : isCutBridge(k);
    }

    private static boolean isCutVertex(int k) {
        return elist[k].size() > 1;
    }

    private static boolean isCutBridge(int k) {
        return true;
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32) {
            n = (n << 1) + (n << 3) + (c & 15);
        }

        return n;
    }
}