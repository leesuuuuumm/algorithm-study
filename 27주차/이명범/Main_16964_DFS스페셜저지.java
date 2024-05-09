import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    static int N;
    static List<Integer>[] elist;
    static int[] order;
    static int[] sorted;

    public static void main(String[] args) throws Exception {
        N = read();
        elist = new List[N + 1];
        order = new int[N];
        sorted = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            elist[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            int from = read();
            int to = read();

            elist[from].add(to);
            elist[to].add(from);
        }

        for (int i = 0; i < N; i++) {
            order[i] = read();
            sorted[order[i]] = i;
        }

        for (List<Integer> list : elist) {
            list.sort(Comparator.comparingInt(o -> sorted[o]));
        }

        System.out.println(dfs(1));
    }
    static int idx = 0;
    private static int dfs(int cur) {
        if (++idx == N) return 1;

        for (Integer next : elist[cur]) {
            if (order[idx] != next) continue;

            if (dfs(next) == 1) return 1;
        }

        return 0;
    }


    private static int read() throws Exception {
        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }
}