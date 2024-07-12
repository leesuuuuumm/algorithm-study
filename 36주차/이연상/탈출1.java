// [BOJ] 탈출1

import java.io.*;
import java.util.*;

public class Main {
    static int atoi(String str) {
        return Integer.parseInt(str);
    }
    static int row, col;
    static char A[][];
    static boolean visit[][];
    static Queue<Point> q = new ArrayDeque<>();
    static Point end, start;
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void pro() {
        bfs(start);
    }

    static void bfs(Point st) {
        q.offer(start);
        visit[st.x][st.y] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();

            if(p.x == end.x && p.y == end.y){
                System.out.println(p.cnt);
                return;
            }

            if(A[p.x][p.y] == '*'){
                for (int i = 0; i < 4; i++) {
                    int X = p.x + dx[i];
                    int Y = p.y + dy[i];

                    if(!isRangeTrue(X,Y)) continue;
                    if(visit[X][Y]) continue;
                    if(A[X][Y] == 'D' || A[X][Y] == 'X') continue;

                    q.offer(new Point(X, Y, p.cnt));
                    visit[X][Y] = true;
                    A[X][Y] = '*';
                }
            }
            else{
                for (int i = 0; i < 4; i++) {
                    int X = p.x + dx[i];
                    int Y = p.y + dy[i];

                    if(!isRangeTrue(X,Y)) continue;
                    if(visit[X][Y]) continue;
                    if(A[X][Y] == '*' || A[X][Y] == 'X') continue;

                    q.offer(new Point(X, Y, p.cnt + 1));
                    visit[X][Y] = true;
                }
            }

        }
        System.out.println("KAKTUS");
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = atoi(st.nextToken());
        col = atoi(st.nextToken());

        A = new char[row][col];
        visit = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            String s = br.readLine();
            for (int j = 0; j < col; j++) {
                A[i][j] = s.charAt(j);
                if(A[i][j] == '*') q.offer(new Point(i, j, 0));
                if(A[i][j] == 'D') end = new Point(i, j, 0);
                if(A[i][j] == 'S') start = new Point(i, j, 0);
            }
        }
    }

    static boolean isRangeTrue(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }

    static class Point{
        int x, y, cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}