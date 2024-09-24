import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 정육면체한번더굴리기 {
    static int N, M;
    static int[][] map;
    static int[][] scoreMap;

    //오른, 아래, 왼, 위
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int t;
    static int score;
    static Cube cube;
    static int[] pos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        scoreMap = new int[N][N];
        cube = new Cube(6, 1, 2, 5, 4, 3, 0);
        pos = new int[2];
        t = 1;
        calculateScoreMap();
        while (t <= M) {
            moveCube();
            calculateScore();
            t++;
        }
        System.out.println(score);
    }

    public static void calculateScoreMap() {
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    int curNum = map[i][j];
                    Queue<int[]> que = new ArrayDeque<>();
                    que.add(new int[]{i, j});
                    visited[i][j] = true;
                    int curScore = curNum;
                    ArrayList<int[]> list = new ArrayList<>();
                    list.add(new int[]{i, j});
                    while (!que.isEmpty()) {
                        int[] node = que.poll();
                        for (int k = 0; k < 4; k++) {
                            int nx = node[0] + dx[k];
                            int ny = node[1] + dy[k];
                            if (isInRange(nx, ny) && !visited[nx][ny] && map[nx][ny] == curNum) {
                                curScore += curNum;
                                que.add(new int[]{nx, ny});
                                visited[nx][ny] = true;
                                list.add(new int[]{nx, ny});
                            }
                        }
                    }
                    for (int[] l : list) {
                        scoreMap[l[0]][l[1]] = curScore;
                    }
                }
            }
        }

    }

    public static void moveCube() {
        //주사위 굴리기
        //(밑에 판)방향 정하기 : 처음에는 항상 오른 쪽
        if (t == 1) {
            pos[0] += dx[0];
            pos[1] += dy[0];
        } else {
            //이때, 주사위의 아랫면이 보드의 해당 칸에 있는 숫자보다 크면 현재 진행방향에서 90' 시계방향으로 회전하여 다시 이동을 진행하게 되고,
            // 주사위의 아랫면의 숫자가 더 작다면 현재 진행방향에서 90' 반시계방향으로 회전하게 되며,
            // 동일하다면 현재 방향으로 계속 진행하게 됩니다.
            // 만약 진행 도중 격자판을 벗어나게 된다면, 반사되어 방향이 반대로 바뀌게 된 뒤 한 칸 움직이게 됩니다.
            int cx = pos[0];
            int cy = pos[1];
            int dir = cube.dir;
            if (map[cx][cy] < cube.bottom) {
                dir = (cube.dir + 1) % 4;
            } else if (map[cx][cy] > cube.bottom) {
                dir = (cube.dir + 3) % 4;
            }
            int nx = cx + dx[dir];
            int ny = cy + dy[dir];
            if (!isInRange(nx, ny)) {
                dir = (dir + 2) % 4;
            }
            pos[0] += dx[dir];
            pos[1] += dy[dir];
            cube.dir = dir;
        }

        //그 방향대로 주사위 굴리기
        cube.move(cube.dir);


    }

    public static void calculateScore() {
        // 격자판 위 주사위가 놓여있는 칸에 적혀있는 숫자와
        // 상하좌우로 인접하며 같은 숫자가 적혀있는 모든 칸의 합만큼 점수를 얻게 됩니다.

        score += scoreMap[pos[0]][pos[1]];
    }

    static boolean isInRange(int nx, int ny) {
        return (nx >= 0 && ny >= 0 && nx < N && ny < N);
    }

    static class Cube {
        public int bottom, top, front, back, left, right;
        public int dir;

        Cube(int bottom, int top, int front, int back, int left, int right, int dir) {
            this.bottom = bottom;
            this.top = top;
            this.front = front;
            this.back = back;
            this.left = left;
            this.right = right;
            this.dir = dir;
        }

        public void move(int dir) {
            int tempBottom = bottom;
            int tempTop = top;
            if (dir == 0) {
                bottom = right;
                top = left;
                left = tempBottom;
                right = tempTop;
            } else if (dir == 1) {
                bottom = front;
                top = back;
                front = tempTop;
                back = tempBottom;
            } else if (dir == 2) {
                bottom = left;
                top = right;
                left = tempTop;
                right = tempBottom;
            } else {
                bottom = back;
                top = front;
                front = tempBottom;
                back = tempTop;
            }
        }
    }
}
