import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    각 모서리는 제외(이러면 화단 밖으로 나갈 경우는 고려할 필요 x)
    한 위치에 씨앗을 심고 다음 위치로 이동해서 거기서 check 수행(꽃잎이 겹치는지)
    조건을 충족하면 최소비용을 갱신하고 백트래킹 맵 끝까지 수행
    최소비용 출력
     */
    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recursion(0, 0);

        System.out.println(ans);
    }

    static void recursion(int cnt, int sum) {
        if (cnt == 3) {
            ans = Math.min(ans, sum);
            return;
        }

        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (!visited[i][j] && check(i, j)) {
                    visited[i][j] = true;
                    recursion(cnt + 1, sum + cost(i, j));
                    clean(i, j);
                }
            }
        }
    }

    static boolean check(int x, int y) { // 꽃잎 겹치는지 확인
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (visited[nx][ny]) {
                return false;
            }
        }

        return true;
    }

    static int cost(int x, int y) { // 현재 꽃의 비용 계산
        int cost = 0;

        cost += map[x][y];
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            visited[nx][ny] = true;
            cost += map[nx][ny];
        }

        return cost;
    }

    static void clean(int x, int y) { // 현재 꽃의 방문 fasle로 되돌리기
        visited[x][y] = false;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            visited[nx][ny] = false;
        }
    }
}