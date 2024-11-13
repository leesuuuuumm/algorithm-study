// [BOJ] 1987_알파벳

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Alphabet {
    static int R, C;
    static char[][] board;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int answer = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        R = scanner.nextInt();
        C = scanner.nextInt();
        scanner.nextLine(); // Line break

        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = scanner.nextLine();
            board[i] = line.toCharArray();
        }

        BFS(0, 0);
        System.out.println(answer);
    }

    static void BFS(int x, int y) {
        Set<String> q = new HashSet<>();
        q.add(x + "," + y + "," + board[x][y]);

        while (!q.isEmpty()) {
            String[] state = q.iterator().next().split(",");
            q.remove(q.iterator().next());
            x = Integer.parseInt(state[0]);
            y = Integer.parseInt(state[1]);
            String ans = state[2];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < R && ny >= 0 && ny < C && !ans.contains(String.valueOf(board[nx][ny]))) {
                    q.add(nx + "," + ny + "," + ans + board[nx][ny]);
                    answer = Math.max(answer, ans.length() + 1);
                }
            }
        }
    }
}