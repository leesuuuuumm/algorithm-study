import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 마법의숲탐색 {
    static int[][] map;
    static int r, c;
    static int[] maxScore;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static boolean[][] isEscape;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new int[r + 4][c + 1];
        isEscape = new boolean[r + 4][c + 1];

        int k = Integer.parseInt(st.nextToken());
        maxScore = new int[k + 1];

        int score = 0;
        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());
            int startCol = Integer.parseInt(st.nextToken());
            int escapeDir = Integer.parseInt(st.nextToken());
            maxScore[i] = getScore(startCol, escapeDir, i);
            if (maxScore[i] > 0) {
                maxScore[i] -= 3;
                score += maxScore[i];
            }

        }
        System.out.println(score);
    }

    static int getScore(int startCol, int escapeDir, int k) {
        //만약 골렘이 최대한 남쪽으로 이동했지만 골렘의 몸 일부가 여전히 숲을 벗어난 상태라면
        // 해당 골렘을 포함해 숲에 위치한 모든 골렘들은 숲을 빠져나간 뒤 다음 골렘부터 새롭게 숲의 탐색을 시작합니다.
        //끝까지 밑으로 내려가기
        int midCol = startCol; //정령 위치
        int midRow = 2;
        while (midRow < r + 2) {
            if (canGo(midRow, midCol, 2)) {
                midRow += 1;
            } else if (canGo(midRow, midCol, 3)) {//서 가능?
                midCol -= 1;
                midRow += 1;
                escapeDir = (escapeDir + 4 - 1) % 4;
            } else if (canGo(midRow, midCol, 1)) {//동 가능?
                midCol += 1;
                midRow += 1;
                escapeDir = (escapeDir + 1) % 4;
            } else {//아무데도 이동 불가하면 스탑
                break;
            }
        }

        //이 위치 저장하기
        if (midRow < 5) {
            map = new int[r + 4][c + 1];
            isEscape = new boolean[r + 4][c + 1];
            return 0;
        }
        map[midRow][midCol] = k;
        map[midRow + 1][midCol] = k;
        map[midRow][midCol - 1] = k;
        map[midRow][midCol + 1] = k;
        map[midRow - 1][midCol] = k;

        int[] escape = translateDir(midRow, midCol, escapeDir);
        isEscape[escape[0]][escape[1]] = true;
        //최대로 밑으로 내려갈 수 있는 점수 계산하기
        Queue<int[]> que = new ArrayDeque<>();
        int max = 0;
        que.add(new int[]{midRow, midCol});
        boolean[][] visited = new boolean[r + 4][c + 1];
        visited[midRow][midCol] = true;
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int row = cur[0];
            int col = cur[1];
            max = Math.max(max, row);
            //escape인 경우
            for (int i = 0; i < 4; i++) {
                int curRow = row + dr[i];
                int curCol = col + dc[i];
                if (curRow > r + 3 || curRow < 3 || curCol > c || curCol < 1 || visited[curRow][curCol] || map[curRow][curCol] == 0)
                    continue;

                if (map[curRow][curCol] == map[row][col] || isEscape[row][col]) {
                    visited[curRow][curCol] = true;
                    que.add(new int[]{curRow, curCol});
                }

            }
        }
        return max;
    }

    static boolean canGo(int midRow, int midCol, int dir) {
        if (dir == 2) { // 남쪽 이동
            for (int i = 0; i < 4; i++) {
                int row = midRow + dr[i] + 1;
                int col = midCol + dc[i];
                if (row > r + 3 || map[row][col] != 0) {
                    return false;
                }
            }
        } else if (dir == 1) { // 동쪽 이동
            if (midCol + 2 > c) return false;
            for (int i = 0; i < 4; i++) {
                int row = midRow + dr[i];
                int col = midCol + dc[i] + 1;
                if (col > c || map[row][col] != 0) {
                    return false;
                }
            }
            // 동쪽으로 이동 후 남쪽으로 갈 수 있는지 확인
            return canGo(midRow, midCol + 1, 2);
        } else if (dir == 3) { // 서쪽 이동
            if (midCol - 2 < 1) return false;
            for (int i = 0; i < 4; i++) {
                int row = midRow + dr[i];
                int col = midCol + dc[i] - 1;
                if (col < 1 || map[row][col] != 0) {
                    return false;
                }
            }
            // 서쪽으로 이동 후 남쪽으로 갈 수 있는지 확인
            return canGo(midRow, midCol - 1, 2);
        }
        return true;
    }

    static int[] translateDir(int midRow, int midCol, int dir) {
        switch (dir) {
            case 0:
                return new int[]{midRow - 1, midCol};
            case 1:
                return new int[]{midRow, midCol + 1};
            case 2:
                return new int[]{midRow + 1, midCol};
            case 3:
                return new int[]{midRow, midCol - 1};
        }
        return new int[]{midRow, midCol};
    }

}