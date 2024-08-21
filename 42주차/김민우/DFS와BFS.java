import java.io.*;
import java.util.*;


public class DFS와BFS {

    static int n, m, v;
    static List<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 정점의 개수
        m = Integer.parseInt(st.nextToken()); // 간선의 개수
        v = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점의 번호
        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start].add(end);
            graph[end].add(start);
        }

        for (int i = 1; i <= n; i++) {
            Collections.sort(graph[i]);
        }

        dfs(v);
        System.out.println();
        visited = new boolean[n + 1];
        bfs(v);
        System.out.println();

    }

    static void dfs(int start) {
        visited[start] = true;
        System.out.print(start+" ");

        for (int num : graph[start]) {
            if (!visited[num]) {
                dfs(num);
            }
        }

    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int current = q.poll();
            System.out.print(current + " ");
            for (int num : graph[current]) {
                if (!visited[num]) {
                    q.offer(num);
                    visited[num] = true;
                }
            }
        }
    }
}
