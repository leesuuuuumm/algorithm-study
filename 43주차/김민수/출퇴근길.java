import java.io.*;
import java.util.*;

public class 출퇴근길 {

    static List<Integer>[] v1 = new ArrayList[100001];
    static List<Integer>[] v2 = new ArrayList[100001];
    static boolean[] visited1 = new boolean[100001];
    static boolean[] visited2 = new boolean[100001];
    static boolean[] visited3 = new boolean[100001];
    static boolean[] visited4 = new boolean[100001];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 초기화
        for (int i = 0; i <= n; i++) {
            v1[i] = new ArrayList<>();
            v2[i] = new ArrayList<>();
        }

        // 간선 입력 받기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            v1[x].add(y); // 정방향 그래프
            v2[y].add(x); // 역방향 그래프
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());


        // 출근길에는 e를 1번만 방문, 퇴근길에는 s를 한번만 방문
        visited1[e] = true;
        visited2[s] = true;
        dfs(s, visited1, v1); // S -> T 진행시 방문 노드
        dfs(e, visited2, v1); // T -> S 진행시 방문 노드
        dfs(s, visited3, v2); // S에서 출발하여 S로 다시 돌아올 수 있는 노드
        dfs(e, visited4, v2); // T에서 출발하여 T로 다시 돌아올 수 있는 노드

        // 조건에 맞는 노드 개수 세기
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (visited1[i] && visited2[i] && visited3[i] && visited4[i]) {
                answer++;
            }
        }

        System.out.println(answer - 2); // S와 T를 제외한 노드 개수
    }

    public static void dfs(int cur, boolean[] visited, List<Integer>[] graph) {
        if (visited[cur]) return;
        visited[cur] = true;
        for (int next : graph[cur]) {
            dfs(next, visited, graph);
        }
    }}
