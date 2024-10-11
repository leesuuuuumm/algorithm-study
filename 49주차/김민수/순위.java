public class 순위 {

    public int solution(int n, int[][] results) {
        boolean[][] graph = new boolean[n+1][n+1];

        for (int[] result : results) {
            int winner = result[0];
            int loser = result[1];
            graph[winner][loser] = true;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (graph[i][k] && graph[k][j]) {
                        graph[i][j] = true;
                    }
                }
            }
        }

        // 정확하게 순위를 매길 수 있는 선수 수를 계산
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            int count = 0;

            for (int j = 1; j <= n; j++) {
                if (graph[i][j] || graph[j][i]) {
                    count++;
                }
            }

            // 자기 자신을 제외한 모든 선수와 승패가 결정되면 순위를 매길 수 있음
            if (count == n - 1) {
                answer++;
            }
        }

        return answer;
    }
}
