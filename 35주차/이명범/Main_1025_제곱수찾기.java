import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static int result = -1;

    public static void main(String[] args) throws Exception {
        input();
        for (int i = -N; i < N; i++) {
            for (int j = -M; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    for (int l = 0; l < M; l++) {
                        if (i == 0 && j == 0) {
                            int sqrt = (int) Math.sqrt(map[k][l]);
                            if (sqrt * sqrt == map[k][l]) {
                                result = Math.max(result, map[k][l]);
                            }
                            continue;
                        }

                        int r = k;
                        int c = l;
                        int num = 0;
                        while (!(r < 0 || r >= N || c < 0 || c >= M)) {
                            num = 10 * num + map[r][c];
                            r += i;
                            c += j;
                            int sqrt = (int) Math.sqrt(num);
                            if (sqrt * sqrt == num) {
                                result = Math.max(result, num);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(result);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
    }
}