import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 2; j++) {
                for (int k = 0; k < n; k++) {
                    for (int h = 0; h < n - 2; h++) {
                        if (i == k && Math.abs(j - h) <= 2) continue;
                        ans = Math.max(ans, map[i][j] + map[i][j + 1] + map[i][j + 2] + map[k][h] + map[k][h + 1] + map[k][h + 2]);
                    }
                }
            }
        }

        System.out.print(ans);
    }
}
