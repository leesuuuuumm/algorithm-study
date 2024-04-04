import java.util.ArrayList;
import java.util.List;

public class Main {
    static class Tetromino {
        int r;
        int c;
        boolean[][] flag;

        public Tetromino(int r, int c, boolean[][] flag) {
            this.r = r;
            this.c = c;
            this.flag = flag;
        }

        public Tetromino rotate() {
            int nr = c;
            int nc = r;
            boolean[][] nf = new boolean[nr][nc];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (!flag[i][j]) continue;

                    int ni = j;
                    int nj = r - 1 - i;
                    nf[ni][nj] = true;
                }
            }
            return new Tetromino(nr, nc, nf);
        }

        public boolean hasPolyomino(int r, int c) {
            return flag[r][c];
        }
    }

    static int N, M;
    static int[][] map;
    static List<Tetromino> tetrominos = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        N = read();
        M = read();
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = read();
            }
        }

        tetrominos.add(new Tetromino(1, 4, flag(0)));
        tetrominos.add(new Tetromino(2, 2, flag(1)));
        tetrominos.add(new Tetromino(3, 2, flag(2)));
        tetrominos.add(new Tetromino(3, 2, flag(3)));
        tetrominos.add(new Tetromino(2, 3, flag(4)));
        tetrominos.add(new Tetromino(3, 2, flag(5)));
        tetrominos.add(new Tetromino(3, 2, flag(6)));
        int size = tetrominos.size();
        for (int i = 0; i < size; i++) {
            if (i == 1) continue;
            if (i == 0 || i == 3 || i == 6) {
                tetrominos.add(tetrominos.get(i).rotate());
                continue;
            }

            Tetromino prev = tetrominos.get(i);
            for (int j = 0; j < 3; j++) {
                Tetromino cur = prev.rotate();
                tetrominos.add(cur);
                prev = cur;
            }
        }

        int max = 0;
        for (Tetromino cur : tetrominos) {
            for (int i = 0; i <= N - cur.r; i++) {
                for (int j = 0; j <= M - cur.c; j++) {
                    int sum = 0;
                    for (int k = i; k < i + cur.r; k++) {
                        for (int l = j; l < j + cur.c; l++) {
                            if (!cur.hasPolyomino(k - i, l - j)) continue;
                            sum += map[k][l];
                        }
                    }
                    max = Math.max(sum, max);
                }
            }
        }
        System.out.println(max);
    }

    private static boolean[][] flag(int mode) {
        boolean[][] flag;
        if (mode == 0) {
            flag = new boolean[1][4];
            flag[0][0] = true;
            flag[0][1] = true;
            flag[0][2] = true;
            flag[0][3] = true;
        } else if (mode == 1) {
            flag = new boolean[2][2];
            flag[0][0] = true;
            flag[0][1] = true;
            flag[1][0] = true;
            flag[1][1] = true;
        } else if (mode == 2) {
            flag = new boolean[3][2];
            flag[0][0] = true;
            flag[1][0] = true;
            flag[2][0] = true;
            flag[2][1] = true;
        } else if (mode == 3) {
            flag = new boolean[3][2];
            flag[0][0] = true;
            flag[1][0] = true;
            flag[1][1] = true;
            flag[2][1] = true;
        } else if (mode == 4) {
            flag = new boolean[2][3];
            flag[0][0] = true;
            flag[0][1] = true;
            flag[0][2] = true;
            flag[1][1] = true;
        } else if (mode == 5) {
            flag = new boolean[3][2];
            flag[0][1] = true;
            flag[1][1] = true;
            flag[2][1] = true;
            flag[2][0] = true;
        } else {
            flag = new boolean[3][2];
            flag[0][1] = true;
            flag[1][1] = true;
            flag[1][0] = true;
            flag[2][0] = true;
        }
        return flag;
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}