import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Gear {
        int[] poles = new int[8];
        int index = 0;

        public Gear(String state) {
            for (int i = 0; i < 8; i++) {
                poles[i] = state.charAt(i) - '0';
            }
        }

        public void rotate(int dir) {
            index = ((index + 8) - dir) % 8;
        }

        public int getLeft() {
            return poles[(index + 6) % 8];
        }

        public int getRight() {
            return poles[(index + 2) % 8];
        }

        public int getTop() {
            return poles[index];
        }
    }

    static class Chain {
        Gear[] gears = new Gear[5];

        public void add(Gear gear) {
            for (int i = 1; i <= 4; i++) {
                if (gears[i] != null) continue;
                gears[i] = gear;
                return;
            }
        }

        public void chaining(int cur, int dir) {
            left(cur, dir);
            right(cur, dir);
            gears[cur].rotate(dir);
        }

        private void left(int cur, int dir) {
            if (cur == 1) return;
            if (gears[cur - 1].getRight() == gears[cur].getLeft()) return;
            left(cur - 1, -dir);
            gears[cur - 1].rotate(-dir);
        }

        private void right(int cur, int dir) {
            if (cur == 4) return;
            if (gears[cur].getRight() == gears[cur + 1].getLeft()) return;
            right(cur + 1, -dir);
            gears[cur + 1].rotate(-dir);
        }

        public int result() {
            int sum = 0;
            for (int i = 1; i <= 4; i++) {
                sum += gears[i].getTop() * (1 << (i - 1));
            }
            return sum;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Chain chain = new Chain();

        for (int i = 0; i < 4; i++) {
            chain.add(new Gear(br.readLine()));
        }

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int idx = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            chain.chaining(idx, dir);
        }

        System.out.println(chain.result());
    }
}