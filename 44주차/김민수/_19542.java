import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _19542 {
    static int N, S, D;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int[] depth;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        graph = new ArrayList[N + 1];
        depth = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start].add(end);
            graph[end].add(start);
        }
        dfs(S, -1);
        System.out.println(result*2);

    }

    public static int dfs(int curIdx, int pastIdx) {
        for (int idx : graph[curIdx]) {
            if(idx!=pastIdx) {
                depth[curIdx]=Math.max(depth[curIdx], dfs(idx, curIdx)+1);
            }
        }
        if(curIdx!=S&&depth[curIdx]>=D){
            result+=1;
        }
        return depth[curIdx];
    }

}
