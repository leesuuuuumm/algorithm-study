import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void move(Point dest) {
            x = dest.x;
            y = dest.y;
        }
    }

    static int min;
    static Point src, dest;
    static Point[] t1, t2;
    static int[] numbers;
    static int R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int xs = Integer.parseInt(st.nextToken());
        int ys = Integer.parseInt(st.nextToken());

        src = new Point(xs, ys);

        st = new StringTokenizer(br.readLine());

        int xe = Integer.parseInt(st.nextToken());
        int ye = Integer.parseInt(st.nextToken());

        dest = new Point(xe, ye);

        t1 = new Point[3];
        t2 = new Point[3];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            t1[i] = new Point(x1, y1);
            t2[i] = new Point(x2, y2);
        }

        min = calculateDistance(src, dest);

        for (int i = 1; i < 6; i++) {
            R = i;
            numbers = new int[i];

            permutation(0, 0);
        }

        System.out.println(min);
    }

    private static int calculateDistance(Point start, Point end) {
        return Math.abs(start.x - end.x) + Math.abs(start.y - end.y);
    }

    private static void permutation(int cnt, int flag) {
        if (cnt == R) {
            long time = 0;

            Point cur = new Point(src.x, src.y);

            if (numbers[0] % 2 == 0) {
                time += calculateDistance(src, t1[numbers[0] / 2]);
                cur.move(t2[numbers[0] / 2]);
            } else {
                time += calculateDistance(src, t2[numbers[0] / 2]);
                cur.move(t1[numbers[0] / 2]);
            }

            time += 10;

            for (int i = 1; i < cnt; i++) {
                if (numbers[i] % 2 == 0) {
                    time += calculateDistance(cur, t1[numbers[i] / 2]);
                    cur.move(t2[numbers[i] / 2]);
                } else {
                    time += calculateDistance(cur, t2[numbers[i] / 2]);
                    cur.move(t1[numbers[i] / 2]);
                }
                time += 10;
            }
            time += calculateDistance(cur, dest);
            
            if (time > Integer.MAX_VALUE) return;
            
            int result = (int) time;
            
            min = Math.min(min, result);

            return;
        }

        for (int i = 0; i < 6; i++) {
            if ((flag & 1 << i) != 0) continue;

            numbers[cnt] = i;

            permutation(cnt + 1, flag | 1 << i);
        }
    }
}