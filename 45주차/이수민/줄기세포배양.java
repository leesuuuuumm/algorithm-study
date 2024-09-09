import java.io.*;
import java.util.*;
 
public class Solution {
    static class Point implements Comparable<Point> {
        int r;
        int c;
        int active;
        int die;
        int hp;
 
        public Point(int r, int c, int active, int die, int hp) {
            this.r = r;
            this.c = c;
            this.active = active;
            this.die = die;
            this.hp = hp;
        }
 
        public int compareTo(Point o) {
            return Integer.compare(o.hp, this.hp);
        }
    }
 
    static int T, N, M, K;
    static int[][] map;
    static Queue<Point> que;
    static Queue<Point> tmp;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[2000][2000];
            que = new LinkedList<>();
            pq = new PriorityQueue<>();
            tmp = new LinkedList<>();
 
            for (int r = 500; r < 500 + N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 500; c < 500 + M; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    if (map[r][c] != 0) {
                        que.offer(new Point(r, c, map[r][c], map[r][c] + map[r][c], map[r][c]));
                    }
                }
            }
 
            for (int k = 1; k < K; k++) {
                while (!que.isEmpty()) {
                    Point cur = que.poll();
 
                    if (cur.active == k) {
                        spreadCell(k, cur);
                    }
                    if (cur.die == k) {
                        map[cur.r][cur.c] = -1;
                        continue;
                    }
                    tmp.offer(new Point(cur.r, cur.c, cur.active, cur.die, cur.hp));
                }
 
                while (!pq.isEmpty()) {
                    Point cur = pq.poll();
                    if (map[cur.r][cur.c] == 0) {
                        map[cur.r][cur.c] = cur.hp;
                        tmp.offer(new Point(cur.r, cur.c, cur.active, cur.die, cur.hp));
                    }
                }
 
                while (!tmp.isEmpty()) {
                    Point cur = tmp.poll();
                    que.offer(new Point(cur.r, cur.c, cur.active, cur.die, cur.hp));
                }
            }
 
            System.out.println("#" + t + " " + countCell());
 
        }
 
    }
 
    private static int countCell() {
        int cnt = 0;
        for (int r = 0; r < 2000; r++) {
            for (int c = 0; c < 2000; c++) {
                if (map[r][c] > 0) {
                    cnt++;
                }
 
            }
        }
        return cnt;
    }
 
    static int[] dr = { 1, 0, -1, 0 };
    static int[] dc = { 0, -1, 0, 1 };
    static PriorityQueue<Point> pq;
 
    private static void spreadCell(int k, Point cur) {
        for (int d = 0; d < 4; d++) {
            int nr = cur.r + dr[d];
            int nc = cur.c + dc[d];
 
            if (map[nr][nc] == 0) {
                pq.offer(new Point(nr, nc, k + cur.hp + 1, k + cur.hp + + cur.hp, cur.hp));
            }
        }
 
    }
 
}
