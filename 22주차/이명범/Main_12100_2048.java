import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int N, result;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        N = read();
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = read();
                result = Math.max(result, map[i][j]);
            }
        }
        bfs();
        System.out.println(result);
    }

    private static void bfs() {
        Queue<int[][]> q = new ArrayDeque<>();
        q.add(map);

        for (int i = 0; i < 5; i++) {
            int size = q.size();
            for (int j = 0; j < size; j++) {
                int[][] cur = q.poll();

                for (int dir = 0; dir < 4; dir++) {
                    int[][] copy = copy(cur);
                    move(dir, copy);
                    q.add(copy);
                }
            }
        }
    }
    private static int[][] copy(int[][] map) {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result[i][j] = map[i][j];
            }
        }
        return result;
    }

    private static void move(int dir, int[][] map) {
        switch(dir) {
            case 0:
                up(map);
                break;
            case 1:
                down(map);
                break;
            case 2:
                left(map);
                break;
            case 3:
                right(map);
                break;
        }
    }

    private static void up(int[][] map) {
        for (int c = 0; c < N; c++) {
            for (int r = 0; r < N; r++) {
                int a = -1;
                int b = -1;
                for (int i = r; i < N; i++) {
                    if (map[i][c] == 0) continue;
                    a = i;
                    break;
                }
                if (a == -1) break;
                for (int i = a + 1; i < N; i++) {
                    if (map[i][c] == 0) continue;
                    b = i;
                    break;
                }
                if (b >= 0 && map[a][c] == map[b][c]) {
                    int sum = map[a][c] + map[b][c];
                    map[a][c] = 0;
                    map[b][c] = 0;
                    map[r][c] = sum;
                    result = Math.max(result, sum);
                } else {
                    map[r][c] = map[a][c];
                    if (r != a) map[a][c] = 0;
                }
            }
        }
    }

    private static void down(int[][] map) {
        for (int c = 0; c < N; c++) {
            for (int r = N - 1; r >= 0; r--) {
                int a = -1;
                int b = -1;
                for (int i = r; i >= 0; i--) {
                    if (map[i][c] == 0) continue;
                    a = i;
                    break;
                }
                if (a == -1) break;
                for (int i = a - 1; i >= 0; i--) {
                    if (map[i][c] == 0) continue;
                    b = i;
                    break;
                }
                if (b >= 0 && map[a][c] == map[b][c]) {
                    int sum = map[a][c] + map[b][c];
                    map[a][c] = 0;
                    map[b][c] = 0;
                    map[r][c] = sum;
                    result = Math.max(result, sum);
                } else {
                    map[r][c] = map[a][c];
                    if (r != a) map[a][c] = 0;
                }
            }
        }
    }

    private static void left(int[][] map) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int a = -1;
                int b = -1;
                for (int i = c; i < N; i++) {
                    if (map[r][i] == 0) continue;
                    a = i;
                    break;
                }
                if (a == -1) break;
                for (int i = a + 1; i < N; i++) {
                    if (map[r][i] == 0) continue;
                    b = i;
                    break;
                }
                if (b >= 0 && map[r][a] == map[r][b]) {
                    int sum = map[r][a] + map[r][b];
                    map[r][a] = 0;
                    map[r][b] = 0;
                    map[r][c] = sum;
                    result = Math.max(result, sum);
                } else {
                    map[r][c] = map[r][a];
                    if (c != a) map[r][a] = 0;
                }
            }
        }
    }

    private static void right(int[][] map) {
        for (int r = 0; r < N; r++) {
            for (int c = N - 1; c >= 0; c--) {
                int a = -1;
                int b = -1;
                for (int i = c; i >= 0; i--) {
                    if (map[r][i] == 0) continue;
                    a = i;
                    break;
                }
                if (a == -1) break;
                for (int i = a - 1; i >= 0; i--) {
                    if (map[r][i] == 0) continue;
                    b = i;
                    break;
                }
                if (b >= 0 && map[r][a] == map[r][b]) {
                    int sum = map[r][a] + map[r][b];
                    map[r][a] = 0;
                    map[r][b] = 0;
                    map[r][c] = sum;
                    result = Math.max(result, sum);
                } else {
                    map[r][c] = map[r][a];
                    if (c != a) map[r][a] = 0;
                }
            }
        }
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}