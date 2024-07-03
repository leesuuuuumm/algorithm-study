import java.util.ArrayList;
import java.util.List;

public class Main {
    static class Shark {
        int r;
        int c;
        int state;
        boolean isUpDown;
        int speed;
        int size;
        boolean isAlive = true;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r - 1;
            this.c = c - 1;
            this.state = getState(parse(d));
            this.isUpDown = d == 1 || d == 2;
            this.speed = s;
            this.size = z;
        }

        private int parse(int d) {
            if (this.r == 0 && d == 1) d = 2;
            else if (this.r == R - 1 && d == 2) d = 1;
            else if (this.c == 0 && d == 4) d = 3;
            else if (this.c == C - 1 && d == 3) d = 4;
            return d;
        }

        private int getState(int d) {
            if (d == 1) {
                return (R - 1) + (R - 1 - this.r);
            } else if (d == 2) {
                return this.r;
            } else if (d == 3) {
                return this.c;
            } else {
                return (C - 1) + (C - 1 - this.c);
            }
        }

        public void move() {
            if (isUpDown) {
                state = (state + speed) % ((R - 1) * 2);
                r = state > R - 1 ? 2 * (R - 1) - state : state;
            } else {
                state = (state + speed) % ((C - 1) * 2);
                c = state > C - 1 ? 2 * (C - 1) - state : state;
            }

            if (temp[r][c] == null) {
                temp[r][c] = this;
            } else if (temp[r][c].size < this.size) {
                temp[r][c].isAlive = false;
                temp[r][c] = this;
            } else {
                this.isAlive = false;
            }
        }
    }

    static int R, C, M;
    static Shark[][] sharkMap;
    static Shark[][] temp;
    static List<Shark> sharks = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        R = read();
        C = read();
        M = read();
        sharkMap = new Shark[R][C];
        temp = new Shark[R][C];

        for (int i = 0; i < M; i++) {
            int r = read();
            int c = read();
            int s = read();
            int d = read();
            int z = read();
            Shark shark = new Shark(r, c, s, d, z);

            sharkMap[r - 1][c - 1] = shark;
            sharks.add(shark);
        }

        int result = 0;
        for (int c = 0; c < C; c++) {
            for (int r = 0; r < R; r++) {
                if (sharkMap[r][c] == null) continue;
                result += sharkMap[r][c].size;
                sharkMap[r][c].isAlive = false;
                break;
            }
            for (Shark shark : sharks) {
                if (!shark.isAlive) continue;
                shark.move();
            }
            sharkMap = temp;
            temp = new Shark[R][C];
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