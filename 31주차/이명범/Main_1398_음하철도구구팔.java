import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int xs = Integer.parseInt(st.nextToken());
        int ys = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int xe = Integer.parseInt(st.nextToken());
        int ye = Integer.parseInt(st.nextToken());
        int dx = Integer.parseInt(st.nextToken());
        int dy = Integer.parseInt(st.nextToken());

        int gcd = gcd(Math.abs(dx), Math.abs(dy));

        dx /= gcd;
        dy /= gcd;

        int cx = xe;
        int cy = ye;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i <= 200; i++) {
            int distance = distance(xe + dx * i, ye + dy * i, xs, ys);

            if (min > distance) {
                cx = xe + dx * i;
                cy = ye + dy * i;
                min = distance;
            }
        }

        System.out.println(cx + " " + cy);
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

//    private static int distance(int x1, int y1, int x2, int y2) {
//        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
//    }


    public static int distance(int a, int b, int x, int y){
        return (a-x) * (a-x) + (b-y) * (b-y);
    }
}