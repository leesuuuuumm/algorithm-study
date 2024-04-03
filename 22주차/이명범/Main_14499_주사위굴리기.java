public class Main {

    static class Dice {
        int r;
        int c;
        int[] pip = new int[6];

        public Dice(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public boolean move(int dir) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if (nr < 0 || nr >= N || nc < 0 || nc >= M) return false;

            r = nr;
            c = nc;

            if (dir == 1) east();
            else if (dir == 2) west();
            else if (dir == 3) north();
            else if (dir == 4) south();

            return true;
        }

        public int getTop() {
            return pip[0];
        }

        public int getBottom() {
            return pip[5];
        }

        public void setBottom(int num) {
            pip[5] = num;
        }

        private void west() {
            int temp = pip[0];
            pip[0] = pip[1];
            pip[1] = pip[5];
            pip[5] = pip[2];
            pip[2] = temp;
        }

        private void east() {
            int temp = pip[0];
            pip[0] = pip[2];
            pip[2] = pip[5];
            pip[5] = pip[1];
            pip[1] = temp;
        }

        private void south() {
            int temp = pip[0];
            pip[0] = pip[3];
            pip[3] = pip[5];
            pip[5] = pip[4];
            pip[4] = temp;
        }

        private void north() {
            int temp = pip[0];
            pip[0] = pip[4];
            pip[4] = pip[5];
            pip[5] = pip[3];
            pip[3] = temp;
        }
    }

    static int[] dr = {0, 0, 0, -1, 1};
    static int[] dc = {0, 1, -1, 0, 0};
    static int N, M, x, y, K;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        N = read();
        M = read();
        x = read();
        y = read();
        K = read();
        map = new int[N][M];

        Dice dice = new Dice(x, y);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = read();
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < K; i++) {
            if (!dice.move(read())) continue;

            int cr = dice.r;
            int cc = dice.c;

            if (map[cr][cc] == 0) {
                map[cr][cc] = dice.getBottom();
            } else {
                dice.setBottom(map[cr][cc]);
                map[cr][cc] = 0;
            }
            result.append(dice.getTop()).append("\n");
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