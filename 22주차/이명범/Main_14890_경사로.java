public class Main {
    static int N, L;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        N = read();
        L = read();
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = read();
            }
        }
        int result = 0;
        for (int r = 0; r < N; r++) {
            boolean[] visit = new boolean[N];
            for (int cur = 0; cur < N; cur++) {
                if (cur == N - 1) {
                    result++;
                    break;
                }
                int next = cur + 1;
                if (map[r][cur] - map[r][next] == 0) {
                } else if (map[r][cur] - map[r][next] == 1) {
                    boolean isBuild = true;
                    for (int tc = next; tc < next + L; tc++) {
                        if (tc >= N || visit[tc] || map[r][tc] != map[r][next]) {
                            isBuild = false;
                            break;
                        }
                        visit[tc] = true;
                    }
                    if (!isBuild) break;
                } else if (map[r][cur] - map[r][next] == -1) {
                    boolean isBuild = true;
                    for (int tc = cur; tc > cur - L; tc--) {
                        if (tc < 0 || visit[tc] || map[r][tc] != map[r][cur]) {
                            isBuild = false;
                            break;
                        }
                        visit[tc] = true;
                    }
                    if (!isBuild) break;
                } else {
                    break;
                }
            }
        }
        for (int c = 0; c < N; c++) {
            boolean[] visit = new boolean[N];
            for (int cur = 0; cur < N; cur++) {
                if (cur == N - 1) {
                    result++;
                    break;
                }
                int next = cur + 1;
                if (map[cur][c] - map[next][c] == 0) {
                } else if (map[cur][c] - map[next][c] == 1) {
                    boolean isBuild = true;
                    for (int tc = next; tc < next + L; tc++) {
                        if (tc >= N || visit[tc] || map[tc][c] != map[next][c]) {
                            isBuild = false;
                            break;
                        }
                        visit[tc] = true;
                    }
                    if (!isBuild) break;
                } else if (map[cur][c] - map[next][c] == -1) {
                    boolean isBuild = true;
                    for (int tc = cur; tc > cur - L; tc--) {
                        if (tc < 0 || visit[tc] || map[tc][c] != map[cur][c]) {
                            isBuild = false;
                            break;
                        }
                        visit[tc] = true;
                    }
                    if (!isBuild) break;
                } else {
                    break;
                }
            }
        }

        System.out.println(result);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}