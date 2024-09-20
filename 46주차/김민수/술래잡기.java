import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 술래잡기 {
    static int N, M, H, K;
    static ArrayList<Integer>[][] hider; //int는 방향을 뜻함
    //0: 오른 1: 왼 2: 아래 3: 위
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int RIGHT = 0;
    static int LEFT = 1;
    static int DOWN = 2;
    static int UP = 3;
    static int[] tagger;
    static ArrayList<int[]> taggerRoute;
    static boolean[][] treeMap;
    static int round;
    static int runnerCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        hider = new ArrayList[N + 1][N + 1];
        treeMap = new boolean[N + 1][N + 1];
        taggerRoute = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                hider[i][j] = new ArrayList<>();
            }
        }
        tagger = new int[2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if (d == 1) {
                hider[x][y].add(0);
            } else {
                hider[x][y].add(2);
            }
        }
        tagger[0] = (N + 1) / 2;
        tagger[1] = (N + 1) / 2;
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            treeMap[x][y] = true;
        }

        round = 1;
        int result = 0;
        makeTaggerRoute();
        for (round = 1; round <= K; round++) {
            runnerCount = 0;
            runAway();
            catchRunner();
            result += (round) * runnerCount;
        }


        System.out.println(result);

    }

    static void runAway() {
        //술래잡기 게임에서는 m명의 도망자가 먼저 동시에 움직임
        //이때 도망자가 움직일 때 현재 술래와의 거리가 3 이하인 도망자만 움직입니다.
        // 도망자의 위치가 (x1, y1), 술래의 위치가 (x2, y2)라 했을 때
        // 두 사람간의 거리는 |x1 - x2| + |y1 - y2|로 정의됩니다.
        ArrayList<int[]> temp = new ArrayList<>();

        for (int i = -3; i <= 3; i++) {
            for (int j = -3; j <= 3; j++) {
                int nx = tagger[0] + i;
                int ny = tagger[1] + j;
                if (Math.abs(tagger[0] - nx) + Math.abs(tagger[1] - ny) <= 3) {
                    if (isInRange(nx, ny) && !hider[nx][ny].isEmpty()) {//해당 칸에 도망자가 있다면
                        ArrayList<Integer> removeIdx = new ArrayList<>();
                        for (int k = 0; k < hider[nx][ny].size(); k++) {
                            int dir = hider[nx][ny].get(k);
                            int hx = nx + dx[dir];
                            int hy = ny + dy[dir];
                            if (isInRange(hx, hy)) {
                                //현재 바라보고 있는 방향으로 1칸 움직인다 했을 때 격자를 벗어나지 않는 경우
                                //
                                //움직이려는 칸에 술래가 있는 경우라면 움직이지 않습니다.
                                //움직이려는 칸에 술래가 있지 않다면 해당 칸으로 이동합니다. 해당 칸에 나무가 있어도 괜찮습니다.
                                if (tagger[0] == hx && tagger[1] == hy) {
                                } else {
                                    removeIdx.add(dir);
                                    temp.add(new int[]{hx, hy, dir});
                                }
                            } else {
                                //현재 바라보고 있는 방향으로 1칸 움직인다 했을 때 격자를 벗어나는 경우
                                //
                                //먼저 방향을 반대로 틀어줍니다. 이후 바라보고 있는 방향으로 1칸 움직인다 했을 때
                                // 해당 위치에 술래가 없다면 1칸 앞으로 이동합니다.
                                int nDir = 0;
                                if (dir == UP) {
                                    nDir = DOWN;
                                } else if (dir == DOWN) {
                                    nDir = UP;
                                } else if (dir == LEFT) {
                                    nDir = RIGHT;
                                } else {
                                    nDir = LEFT;
                                }
                                hx = nx + dx[nDir];
                                hy = ny + dy[nDir];

                                if (tagger[0] == hx && tagger[1] == hy) {

                                } else {
                                    removeIdx.add(dir);
                                    temp.add(new int[]{hx, hy, nDir});
                                }

                            }
                        }
                        hider[nx][ny].removeAll(removeIdx);
                    }
                }
            }
        }
        for (int[] t : temp) {
            int x = t[0];
            int y = t[1];
            int d = t[2];
            hider[x][y].add(d);
        }
        //다음 규칙에 의해


    }

    static void catchRunner() {
        //이동
        int[] dir = taggerRoute.get((round - 1) % (taggerRoute.size()));
        tagger[0] += dir[0];
        tagger[1] += dir[1];

        //술래의 시야는 현재 바라보고 있는 방향을 기준으로 현재 칸을 포함하여 총 3칸
        int[] nDir = taggerRoute.get(round % taggerRoute.size());
        for (int i = 0; i < 3; i++) {
            int nx = tagger[0] + nDir[0] * i;
            int ny = tagger[1] + nDir[1] * i;
            if (isInRange(nx, ny)) {
                if (!treeMap[nx][ny] && !hider[nx][ny].isEmpty()) {
                    runnerCount += hider[nx][ny].size();
                    hider[nx][ny].clear();
                }
            }
        }
    }

    static boolean isInRange(int nx, int ny) {
        return (nx >= 1 && ny >= 1 && nx <= N && ny <= N);
    }

    static void makeTaggerRoute() {
        //끝까지 가기
        // 위 오 아래 왼
        int len = 1;
        while (len < N) {

            for (int k = 0; k < len; k++) {
                taggerRoute.add(new int[]{dx[UP], dy[UP]});
            }
            for (int k = 0; k < len; k++) {
                taggerRoute.add(new int[]{dx[RIGHT], dy[RIGHT]});
            }
            len += 1;
            for (int k = 0; k < len; k++) {
                taggerRoute.add(new int[]{dx[DOWN], dy[DOWN]});
            }
            for (int k = 0; k < len; k++) {
                taggerRoute.add(new int[]{dx[LEFT], dy[LEFT]});
            }
            len += 1;

        }
        len -= 1;
        for (int k = 0; k < len; k++) {
            taggerRoute.add(new int[]{dx[UP], dy[UP]});
        }
        for (int k = 0; k < len; k++) {
            taggerRoute.add(new int[]{dx[DOWN], dy[DOWN]});
        }


        //다시 처음으로
        // 오 위 왼 아래
        while (len >= 1) {
            for (int k = 0; k < len; k++) {
                taggerRoute.add(new int[]{dx[RIGHT], dy[RIGHT]});
            }
            for (int k = 0; k < len; k++) {
                taggerRoute.add(new int[]{dx[UP], dy[UP]});
            }

            len -= 1;
            for (int k = 0; k < len; k++) {
                taggerRoute.add(new int[]{dx[LEFT], dy[LEFT]});
            }
            for (int k = 0; k < len; k++) {
                taggerRoute.add(new int[]{dx[DOWN], dy[DOWN]});
            }
            len -= 1;

        }

    }
}
