import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static double east, west, south, north;

    static boolean[][] map = new boolean[30][30];

    static double result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        east = Integer.parseInt(st.nextToken()) / 100.0;
        west = Integer.parseInt(st.nextToken()) / 100.0;
        south = Integer.parseInt(st.nextToken()) / 100.0;
        north = Integer.parseInt(st.nextToken()) / 100.0;

        calculate(15, 15, 0, 1);

        System.out.println(1 - result);
    }

    private static void calculate(int r, int c, int depth, double percent) {
        if (depth > N) return;
        if (map[r][c]) {
            result += percent;
            return;
        }

        map[r][c] = true;

        // 오른쪽
        if (east != 0) calculate(r, c + 1, depth + 1, percent * east);

        // 왼쪽
        if (west != 0) calculate(r, c - 1, depth + 1, percent * west);

        // 아래
        if (south != 0) calculate(r + 1, c, depth + 1, percent * south);

        // 위
        if (north != 0) calculate(r - 1, c, depth + 1, percent * north);

        map[r][c] = false;
    }
}