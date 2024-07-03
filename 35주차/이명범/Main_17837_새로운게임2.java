import java.util.ArrayList;
import java.util.List;

public class Main {

    static class Piece {
        int r;
        int c;
        int dir;

        Piece parent = null;
        Piece child = null;

        public Piece(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        public boolean process() {
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if (isOutOfBounds(nr, nc) || map[nr][nc] == 2) {
                dir = oppositeDir();
                nr = r + dr[dir];
                nc = c + dc[dir];

                if (isOutOfBounds(nr, nc) || map[nr][nc] == 2) return false;

            }

            if (parent == null) {
                pieces[r][c] = null;
            } else {
                parent.child = null;
                parent = null;
            }

            move(nr, nc);
            Piece root = null;
            if (map[nr][nc] == 1) root = reverse();

            if (pieces[nr][nc] != null) {
                pieces[nr][nc].chain(root == null ? this : root);
            } else {
                pieces[nr][nc] = root == null ? this : root;
            }

            if (pieces[nr][nc].count() >= 4) return true;
            else return false;
        }

        public void chain(Piece n) {
            if (child != null){
                child.chain(n);
            } else {
                child = n;
                n.parent = this;
            }
        }

        private static boolean isOutOfBounds(int nr, int nc) {
            return nr < 1 || nr > N || nc < 1 || nc > N;
        }

        private int oppositeDir() {
            switch (dir) {
                case 1:
                    return 2;
                case 2:
                    return 1;
                case 3:
                    return 4;
                case 4:
                    return 3;
            }
            return -1;
        }

        private void move(int nr, int nc) {
            r = nr;
            c = nc;
            if (child != null) child.move(nr, nc);
        }

        private Piece reverse() {
            Piece root = null;
            if (child != null) root = child.reverse();
            Piece temp = parent;
            parent = child;
            child = temp;
            return parent == null ? this : root;
        }

        private int count() {
            return child == null ? 1 : child.count() + 1;
        }
    }

    static int N, K;
    static int[][] map;
    static Piece[][] pieces;
    static List<Piece> pieceList = new ArrayList<>();
    static int[] dr = new int[]{0, 0, 0, -1, 1};
    static int[] dc = new int[]{0, 1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        N = read(); K = read();
        map = new int[N + 1][N + 1];
        pieces = new Piece[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = read();
            }
        }

        for (int i = 0; i < K; i++) {
            int r = read();
            int c = read();
            int dir = read();
            Piece n = new Piece(r, c, dir);

            if (pieces[r][c] == null) pieces[r][c] = n;
            else pieces[r][c].chain(n);
            pieceList.add(n);
        }
        int turn = 1;
        while (true) {
            if (turn > 1000) {
                System.out.println(-1);
                return;
            }
            for (Piece cur : pieceList) {
                if (cur.process()) {
                    System.out.println(turn);
                    return;
                }
            }
            turn++;
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