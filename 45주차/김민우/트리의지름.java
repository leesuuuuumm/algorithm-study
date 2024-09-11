
import java.io.*;
import java.util.*;

public class 트리의지름 {

    static ArrayList<int[]>[] graph; // new int[0] = 인접한 정점, int[1] = 해당 정점에 이동하는 비용(가중치)
    static int max = Integer.MIN_VALUE;
    static int maxNode = 0;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new int[] {end, cost});
            graph[end].add(new int[] {start, cost});
        }


        // 1번 루트노드를 기준으로, 가장 멀리 있는 노드를 탐색
        dfs(1, 0);
        visited = new boolean[n + 1];

        // 이제 해당 노드에서 가장 먼 노드를 탐색하여, 해당 노드의 비용을 갱신
        int res = 0; // 결과를 담을 노드
        dfs(maxNode, 0);
        res = max;
        System.out.println(res);

    }

    static void dfs(int current, int cost) {
        visited[current] = true;

        // 가장 높은 가중치의 노드를 기록
        if (cost > max) {
            max = cost;
            maxNode = current;
        }

        for (int[] near : graph[current]) {
            int nearTarget = near[0];
            int nearCost = near[1];
            if (!visited[nearTarget]) {
                dfs(nearTarget, cost + nearCost);
            }
        }

    }

}
