import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        int r; int c; int w;

        public Edge(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }
    }
    static int N, M, map[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }


        System.out.println(bfs());
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    private static int bfs() {
        Deque<Edge> dq = new LinkedList<>();
        boolean[][] visit = new boolean[N][M];

        dq.add(new Edge(0, 0, 0));
        visit[0][0] = true;

        int result = 0;

        while (!dq.isEmpty()) {
            Edge cur = dq.poll();


            if (cur.r == N - 1 && cur.c == M - 1) return cur.w;

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (visit[nr][nc]) continue;

                if (map[nr][nc] == 0) dq.addFirst(new Edge(nr, nc, cur.w));
                else dq.addLast(new Edge(nr, nc, cur.w + 1));
                visit[nr][nc] = true;
            }
        }

        return Integer.MAX_VALUE;
    }
}