// [BOJ] 거울 설치

import java.io.*;
import java.util.*;

public class MirrorInstallation {
    static int n;
    static char[][] a;
    static int[][][] c;
    static int sx, sy, fx, fy;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static Deque<int[]> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new char[n][n];
        c = new int[n][n][4];

        List<int[]> doors = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < n; j++) {
                a[i][j] = row.charAt(j);
                if (a[i][j] == '#') {
                    doors.add(new int[]{i, j});
                }
            }
        }

        sx = doors.get(0)[0];
        sy = doors.get(0)[1];
        fx = doors.get(1)[0];
        fy = doors.get(1)[1];

        // 초기 방향 설정
        int initialDir = -1;
        for (int i = 0; i < 4; i++) {
            int nx = sx + dx[i];
            int ny = sy + dy[i];
            if (0 <= nx && nx < n && 0 <= ny && ny < n && a[nx][ny] != '*') {
                initialDir = i;
                break;
            }
        }

        bfs(sx, sy, initialDir);
    }

    static void bfs(int x, int y, int dir) {
        q.add(new int[]{x, y, dir});
        c[x][y][dir] = 1;

        List<Integer> ans = new ArrayList<>();

        while (!q.isEmpty()) {
            int[] current = q.poll();
            x = current[0];
            y = current[1];
            dir = current[2];

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                if (c[nx][ny][dir] == 0 || c[nx][ny][dir] > c[x][y][dir]) {
                    if (a[nx][ny] != '*') {
                        c[nx][ny][dir] = c[x][y][dir];
                        if (nx == fx && ny == fy) {
                            ans.add(c[nx][ny][dir]);
                            continue;
                        }
                        q.add(new int[]{nx, ny, dir});
                        if (a[nx][ny] == '!') {
                            turn(nx, ny, dir);
                        }
                    }
                }
            }
        }

        System.out.println(Collections.min(ans) - 1);
    }

    static void turn(int x, int y, int dir) {
        int[] ndir = {(dir + 1) % 4, (dir + 3) % 4};
        for (int d : ndir) {
            if (c[x][y][d] == 0 || c[x][y][d] > c[x][y][dir] + 1) {
                c[x][y][d] = c[x][y][dir] + 1;
                q.add(new int[]{x, y, d});
            }
        }
    }
}