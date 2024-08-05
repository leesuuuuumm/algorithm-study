import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 직각삼각형 {
    static int N;
    static long[][] points;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        points = new long[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i][0] = Long.parseLong(st.nextToken());
            points[i][1] = Long.parseLong(st.nextToken());
        }

        int count = 0;
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (isRightAngle(i, j, k))
                        count += 1;
                }
            }
        }

        System.out.println(count);
    }

    static boolean isRightAngle(int i, int j, int k) {
        long x1 = points[i][0];
        long x2 = points[j][0];
        long x3 = points[k][0];
        long y1 = points[i][1];
        long y2 = points[j][1];
        long y3 = points[k][1];

        long dist1 = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
        long dist2 = (x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3);
        long dist3 = (x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3);
        return (dist1 + dist2 == dist3 || dist2 + dist3 == dist1 || dist1 + dist3 == dist2);
    }
}