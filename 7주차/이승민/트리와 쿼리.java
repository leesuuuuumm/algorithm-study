import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    /*

     */
    static int n, r, q;
    static ArrayList<Integer>[] adj;
    static int[] size;
    static boolean[] vis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        size = new int[n + 1];
        vis = new boolean[n + 1];
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        dfs(r);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int u = Integer.parseInt(br.readLine());
            sb.append(size[u] + "\n");
        }

        System.out.println(sb);
    }

    static void dfs(int node) {
        if (vis[node]) {
            return;
        }

        vis[node] = true;
        size[node] = 1; // 자기 자신도 서브트리에 포함되므로 1로 시작

        for (int child : adj[node]) {
            if (!vis[child]) {
                dfs(child); // 이렇게 하면 size[child]에 1이 담기게 되고 전부 size[부모]에 합산된다.
                size[node] += size[child];
            }
        }
    }
}
