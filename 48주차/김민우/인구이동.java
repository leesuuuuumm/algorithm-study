
import java.io.*;
import java.util.*;

public class 인구이동 {
    static int n, l, r;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static ArrayList<int[]> list;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        graph = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();

    }

    static void solve() {
        int cnt = 0;
        while (true) { // 인구 이동이 불가능할 때까지 반복
            boolean isMove = false;
            visited = new boolean[n][n]; // 방문처리 초기화

            // n, m 순회 (각 점을 순회하며, 인구 이동이 가능한 원소를 찾는다.)
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    // 만약 특정 점에 bfs가 수행된다면, 방문처리가 되어있을테니, 재방문 하지 않도록 로직을 구현함.
                    if (!visited[i][j]) {
                        int sum = bfs(i, j); // 각 점에 대해 bfs 순회

                        // 인구 이동이 가능하다면
                        if (list.size() > 1) {
                            changePopulation(sum); // list에 담긴 원소에 평균 값 추가
                            isMove = true;
                        }
                    }
                }
            }
            // 인구 이동이 없다면, 지금까지 센 인구이동 수를 출력하고 종료.
            if (!isMove) {
                System.out.println(cnt);
                return;
            }
            // 인구 이동 횟수 증가
            cnt++;
        }
    }


    static int bfs(int x, int y) {
        visited[x][y] = true;
        list = new ArrayList<>();
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {x, y});
        list.add(new int[] {x, y});
        // 탐색 대상 큐 삽입

        // sum := 합쳐진 연합의 인구 수
        int sum = graph[x][y];
        while (!que.isEmpty()) {
            int[] current = que.poll();

            for (int d = 0; d < 4; d++) {
                int nx = current[0] + dx[d];
                int ny = current[1] + dy[d];
                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny]) {
                    if (Math.abs(graph[current[0]][current[1]] - graph[nx][ny]) <= r && Math.abs(graph[current[0]][current[1]] - graph[nx][ny]) >= l) {
                        visited[nx][ny] = true;
                        que.offer(new int[] {nx, ny});
                        list.add(new int[] {nx, ny});
                        sum += graph[nx][ny];
                    }
                }
            }
        }
        return sum;
    }

    static void changePopulation(int sum) {
        int avg = sum / list.size();
        for (int[] n: list) {
            graph[n[0]][n[1]] = avg;
        }
    }

}
