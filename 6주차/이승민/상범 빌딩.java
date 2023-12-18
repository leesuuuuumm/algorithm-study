import java.io.*;
import java.util.*;

class Node {
    int x, y, z;

    public Node(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

public class Main {
    /*
    1초, 128MB

     */

    static int l, r, c;
    static int dl, dr, dc;
    static char[][][] build;
    static int[][][] dist;
    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    static Queue<Node> q;

    public static void main(String[] args) throws Exception {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken()); // 층
            r = Integer.parseInt(st.nextToken()); // 행
            c = Integer.parseInt(st.nextToken()); // 열
            if (l == 0 && r == 0 && c == 0) {
                break;
            }

            build = new char[l][r][c];
            dist = new int[l][r][c];
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < r; j++) {
                    Arrays.fill(dist[i][j], -1);
                }
            }
            q = new LinkedList<>();

            for (int i = 0; i < l; i++) {
                for (int j = 0; j < r; j++) {
                    String line = br.readLine();
                    for (int k = 0; k < c; k++) {
                        build[i][j][k] = line.charAt(k);

                        if (build[i][j][k] == 'S') { // 출발지점 기록
                            dist[i][j][k]++;
                            q.offer(new Node(k, j, i));
                        }
                        if (build[i][j][k] == 'E') { // 도착지점 기록
                            dl = i;
                            dr = j;
                            dc = k;
                        }
                    }
                }
                br.readLine();
            }

            bfs();

            if (dist[dl][dr][dc] == -1) {
                System.out.println("Trapped!");
            } else {
                System.out.println("Escaped in " + dist[dl][dr][dc] + " minute(s).");
            }
        }
    }

    public static void bfs() {
        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 6; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nz = cur.z + dz[i];

                if (nx < 0 || nx >= c || ny < 0 || ny >= r || nz < 0 || nz >= l) {
                    continue;
                }
                if (dist[nz][ny][nx] >= 0 || build[nz][ny][nx] == '#') {
                    continue;
                }

                dist[nz][ny][nx] = dist[cur.z][cur.y][cur.x] + 1;
                q.offer(new Node(nx, ny, nz));
            }
        }
    }
}