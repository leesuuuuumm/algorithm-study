import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열돌리기_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int layers = Math.min(n, m) / 2;
        for (int i = 0; i < layers; i++) {
            int count = r % (2 * (n + m - 4 * i - 2));
            for (int k = 0; k < count; k++) {
                int temp = map[i][i];
                // 위
                for (int j = i; j < m - i - 1; j++) {
                    map[i][j] = map[i][j + 1];
                }
                // 오른쪽
                for (int j = i; j < n - i - 1; j++) {
                    map[j][m - i - 1] = map[j + 1][m - i - 1];
                }
                // 아래
                for (int j = m - i - 1; j > i; j--) {
                    map[n - i - 1][j] = map[n - i - 1][j - 1];
                }
                // 왼쪽
                for (int j = n - i - 1; j > i + 1; j--) {
                    map[j][i] = map[j - 1][i];
                }
                map[i + 1][i] = temp;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}