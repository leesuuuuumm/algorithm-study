// [BOJ] 알파벳

import java.util.*;

public class Main {
    static int R, C;
    static char[][] board;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int answer = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        board = new char[R][C];

        // 보드 입력
        for (int i = 0; i < R; i++) {
            String line = sc.next();
            board[i] = line.toCharArray();
        }

        BFS(0, 0);
        System.out.println(answer);
        sc.close();
    }

    static void BFS(int x, int y) {
        Set<State> q = new HashSet<>();
        q.add(new State(x, y, String.valueOf(board[x][y])));

        while (!q.isEmpty()) {
            State current = q.iterator().next();
            q.remove(current);

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx >= 0 && nx < R && ny >= 0 && ny < C && !current.path.contains(String.valueOf(board[nx][ny]))) {
                    q.add(new State(nx, ny, current.path + board[nx][ny]));
                    answer = Math.max(answer, current.path.length() + 1);
                }
            }
        }
    }

    static class State {
        int x, y;
        String path;

        State(int x, int y, String path) {
            this.x = x;
            this.y = y;
            this.path = path;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, path);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            State state = (State) obj;
            return x == state.x && y == state.y && Objects.equals(path, state.path);
        }
    }
}