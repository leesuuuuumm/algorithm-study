import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Location {
        int r;
        int c;

        public Location(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int rows, cols, map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        rows = Integer.parseInt(st.nextToken());
        cols = Integer.parseInt(st.nextToken());
        map = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < cols; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        System.out.println(time);
        System.out.println(count);
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int time = 0, count;

    private static void bfs() {
        Queue<Location> q = new LinkedList<>();
        Queue<Location> removeQ = new LinkedList<>();
        boolean[][] visit = new boolean[rows][cols];

        q.offer(new Location(0, 0));
        visit[0][0] = true;

        while (!q.isEmpty()) {

            Location cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;
                if (visit[nr][nc]) continue;

                if (map[nr][nc] == 1){
                    removeQ.offer(new Location(nr, nc));
                } else if (map[nr][nc] == 0) {
                    q.offer(new Location(nr, nc));
                }
                visit[nr][nc] = true;
            }

            if (q.isEmpty() && !removeQ.isEmpty()) {
                count = removeQ.size();
                time++;

                q.addAll(removeQ);
                removeQ.clear();
            }
        }
    }
}