import java.util.Arrays;

public class Solution {

    static int[] ryan = new int[11];

    static int maxDiff = Integer.MIN_VALUE;
    static int[] answer = {-1};

    public static int[] solution(int n, int[] info) {
        combination(n, info, 0, 0);
        return answer;
    }

    private static void combination(int n, int[] info, int cnt, int start) {
        if (cnt == n) {
            int apeachScore = 0, ryanScore = 0, flag = 0;

            for (int i = 0; i < 11; i++) {
                if (info[i] == 0 && ryan[i] == 0) continue;

                if (info[i] >= ryan[i]) {
                    apeachScore += 10 - i;
                } else {
                    ryanScore += 10 - i;
                }
            }

            int diff = ryanScore - apeachScore;

            // 어피치가 이기거나 지는 경우는 바로 리턴
            if (diff <= 0) return;

            // diff가 maxDiff보다 작은 경우 리턴
            if (diff < maxDiff) return;

            // diff가 maxDiff와 같은 경우
            if (diff == maxDiff) {
                for (int i = 10; i >= 0; i--) {
                    if (answer[i] > ryan[i]) return;
                    else if (answer[i] < ryan[i]) break;
                }
            }

            maxDiff = diff;
            answer = Arrays.copyOf(ryan, ryan.length);
            return;
        }

        for (int i = start; i < 11; i++) {
            ryan[i]++;
            combination(n, info, cnt + 1, i);
            ryan[i]--;
        }
    }
}