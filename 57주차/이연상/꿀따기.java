// [BOJ] 꿀 따기

import java.util.*;

public class HoneyHarvest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] H = new int[N];
        int[] S = new int[N];
        int result = 0;

        for (int i = 0; i < N; i++) {
            H[i] = scanner.nextInt();
        }

        // 누적합 계산
        S[0] = H[0];
        for (int i = 1; i < N; i++) {
            S[i] = S[i - 1] + H[i];
        }

        // 꿀통이 오른쪽 끝에 있을 때
        for (int i = 1; i < N - 1; i++) {
            result = Math.max(result, 2 * S[N - 1] - H[0] - H[i] - S[i]);
        }

        // 꿀통이 왼쪽 끝에 있을 때
        for (int i = 1; i < N - 1; i++) {
            result = Math.max(result, S[N - 1] - H[N - 1] - H[i] + S[i - 1]);
        }

        // 꿀통이 중간에 있을 때
        for (int i = 1; i < N - 1; i++) {
            result = Math.max(result, S[i] - H[0] + S[N - 1] - S[i - 1] - H[N - 1]);
        }

        System.out.println(result);
    }
}