import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 학부연구생민상 {
    public static int n, m;
    public static ArrayList<int[]> airCon = new ArrayList<>();
    public static int[][] map;
    public static boolean[][] isCool;

    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        isCool = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    airCon.add(new int[]{i, j});
                }
            }
        }
        int result = 0;
        if (airCon.isEmpty()) {
            System.out.println(0);
            System.exit(0);
        }
        for (int[] air : airCon) {
            bfs(air);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (isCool[i][j])
                    result += 1;
            }
        }
        System.out.println(result);

    }

    public static void bfs(int[] airCon) {
        Queue<Wind> que = new ArrayDeque<>();
        isCool[airCon[0]][airCon[1]] = true;
        for (int i = 0; i < 4; i++) {
            que.add(new Wind(airCon[0], airCon[1], i));
        }
        while (!que.isEmpty()) {
            Wind wind = que.poll();
            isCool[wind.x][wind.y] = true;
            if(wind.dir!=5) {
                int nx = wind.x + dx[wind.dir];
                int ny = wind.y + dy[wind.dir];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && map[nx][ny] != 9) {
                    int nDir = changeDir(wind.dir, map[nx][ny]);
                    que.add(new Wind(nx, ny, nDir));
                }
            }
        }

    }

    public static int changeDir(int dir, int wall) {
        switch (wall) {
            case 1: {
                if (dir == 1 || dir == 3)
                    return 5;
                else return dir;
            }
            case 2: {
                if (dir == 0 || dir == 2)
                    return 5;
                else return dir;
            }
            case 3: {
                if (dir == 0)
                    return 3;
                else if (dir == 1)
                    return 2;
                else if (dir == 2)
                    return 1;
                else if (dir == 3)
                    return 0;
                break;
            }
            case 4: {
                if (dir == 0)
                    return 1;
                else if (dir == 1)
                    return 0;
                else if (dir == 2)
                    return 3;
                else if (dir == 3)
                    return 2;
                break;
            }
            default:
                return dir;
        }
        return dir;
    }

    public static class Wind {
        public int x, y, dir;

        public Wind(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
