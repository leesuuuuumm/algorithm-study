import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] point = new int[n][2];
        for (int i = 0; i < n; i++) {
            point[i][0] = sc.nextInt();
            point[i][1] = sc.nextInt();
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i < n - 1; i++) {
            int tmp = 0; // 거리
            int prev = 0;
            for (int j = 1; j < n; j++) {
                if (i == j) continue;
                tmp += Math.abs(point[j][0] - point[prev][0]) + Math.abs(point[j][1] - point[prev][1]);
                prev = j;
            }
            min = Math.min(min, tmp);
        }

        System.out.print(min);
    }
}
