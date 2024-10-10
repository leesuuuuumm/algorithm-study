// [CDT] 술래잡기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int n, m, h, k;
    static int[][] map;
    static ArrayList<int[]> runners;
    static int[][] tree;
    static int nowX, nowY;
    static int answer = 0;

    static int[][] clockMap;
    static int nowDir = 0;

    static int[] dx = {-1, 0, 1, 0}; // 상 우 하 좌 순서
    static int[] dy = {0, 1, 0, -1};
    static int clock = 1;

    static int[][] runnerMove = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상 순서

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        nowX = n / 2;
        nowY = n / 2;
        
        map = new int[n][n];
        clockMap = new int[n][n];
        runners = new ArrayList<>();
        tree = new int[h][2];

        for(int i = 0; i < m; i++) { // 도망자 위치 == 2
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1;
            int[] arr = {x, y, dir};
            runners.add(arr);
        }
        
        for(int i = 0; i < h; i++) { // 나무 위치 표시 == 3
            st = new StringTokenizer(br.readLine());
            tree[i][0] = Integer.parseInt(st.nextToken()) - 1;
            tree[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }

        getClockMap();
        while(k != 0) {
            getTree();
            moveRunner();
            moveNow();
            map = new int[n][n];
            k--;
        }

        System.out.println(answer);
    }

    static void moveRunner() {
        for(int i = 0; i < runners.size(); i++) { // 도둑 이동 & 표시
            int x = runners.get(i)[0];
            int y = runners.get(i)[1];
            int dir = runners.get(i)[2];

            if(Math.abs(x - nowX) + Math.abs(y - nowY) > 3) continue;

            int nx = x + runnerMove[dir][0];
            int ny = y + runnerMove[dir][1];

            if(nx == nowX && ny == nowY) {
                map[x][y] = 2;
                continue;
            }

            if(nx >= n || ny >= n || nx < 0 || ny < 0) {
                if(dir == 0) dir = 2;
                else if(dir == 1) dir = 3;
                else if(dir == 2) dir = 0;
                else if(dir == 3) dir = 1;
                runners.get(i)[2] = dir;

                nx = x + runnerMove[dir][0];
                ny = y + runnerMove[dir][1];

                if(nx == nowX && ny == nowY) {
                    map[x][y] = 2;
                    continue;
                }
            }

            
            if(map[nx][ny] != 3) {
                map[nx][ny] = 2;
            }
            runners.get(i)[0] = nx;
            runners.get(i)[1] = ny;
        }
    }

    static void moveNow() {
        nowX += dx[nowDir] * clock;
        nowY += dy[nowDir] * clock;

        if(clockMap[nowX][nowY] != 5) nowDir = clockMap[nowX][nowY];

        int tempX = nowX;
        int tempY = nowY;
        for(int i = 0; i < 3; i++) {
            int nx = nowX + dx[nowDir];
            int ny = nowY + dy[nowDir];

            if(nx >= n || ny >= n || nx < 0 || ny < 0) {
                break;
            }

            if(map[nx][ny] == 3) {
                nowX = nx;
                nowY = ny;
                continue;
            }
            
            if(map[nx][ny] == 2) {
                for(int j = 0; j < runners.size(); j++) {
                    if(runners.get(j)[0] == nx && runners.get(j)[1] == ny) {
                        runners.remove(j);
                        break;
                    }
                }
                answer += 1;
            }
            nowX = nx;
            nowY = ny;
        }
        nowX = tempX;
        nowY = tempY;

        if(nowX == 0 && nowY == 0) clock = -1;
        if(nowX == (n / 2) && nowY == (n / 2)) clock = 1;
    }

    static void getTree() {
        for(int i = 0; i < h; i++) {
            int x = tree[i][0];
            int y = tree[i][1];
            map[x][y] = 3;
        }
    }

    static void getClockMap() {
        int direction = 0;
        int step = 1;
        int x = n / 2, y = n / 2;
        
        while(step < n) {
            for(int i = 0; i < 2; i++) {
                for(int j = 0; j < step; j++) {
                    x += dx[direction];
                    y += dy[direction];
                    clockMap[x][y] = 5;
                }
                direction = (direction + 1) % 4;
                clockMap[x][y] = direction;
            }
            step += 1;
        }
    }
}