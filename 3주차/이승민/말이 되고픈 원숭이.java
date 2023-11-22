import java.io.*;
import java.util.*;

class Node {
    int x, y;
    int horse;

    public Node(int x, int y, int horse) {
        this.x = x;
        this.y = y;
        this.horse = horse;
    }
}

public class Main {
    /*
    벽 부수기처럼 dist를 3차원 배열로(말처럼 이동하는 횟수 별로 나눠서)
     */
    static int k, w, h;
    static int[][] map;
    static int[][][] dist;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] hdx = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] hdy = {2, 1, -1, -2, -2, -1, 1, 2};
    static Queue<Node> q;

    public static void main(String[] args) throws Exception {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[h][w];
        dist = new int[h][w][k+1];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                Arrays.fill(dist[i][j], -1);
            }
        }
        q = new LinkedList<>();

        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dist[0][0][0] = 0;
        q.offer(new Node(0, 0, 0));
        bfs();

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < k + 1; i++) {
            if (dist[h-1][w-1][i] != -1) {
                min = Math.min(min, dist[h-1][w-1][i]);
            }
        }

        if (min != Integer.MAX_VALUE) {
            System.out.println(min);
        } else {
            System.out.println(-1);
        }
    }

    public static void bfs() {
        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.horse < k) { // 말처럼 이동할 횟수가 남은 경우
                for (int i = 0; i < 8; i++) {
                    int nx = cur.x + hdx[i];
                    int ny = cur.y + hdy[i];
                    int nhorse = cur.horse + 1;

                    if (nx < 0 || nx >= w || ny < 0 || ny >= h) {
                        continue;
                    }

                    if (dist[ny][nx][nhorse] >= 0 || map[ny][nx] == 1) {
                        continue;
                    }

                    dist[ny][nx][nhorse] = dist[cur.y][cur.x][cur.horse] + 1;
                    q.offer(new Node(nx, ny, nhorse));
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nhorse = cur.horse;

                if (nx < 0 || nx >= w || ny < 0 || ny >= h) {
                    continue;
                }

                if (dist[ny][nx][nhorse] >= 0 || map[ny][nx] == 1) {
                    continue;
                }

                dist[ny][nx][nhorse] = dist[cur.y][cur.x][cur.horse] + 1;
                q.offer(new Node(nx, ny, nhorse));
            }
        }
    }
}