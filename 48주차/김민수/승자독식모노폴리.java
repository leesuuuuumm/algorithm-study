import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 승자독식모노폴리 {
    static int n, m, k;
    static int[][][] map;

    //1~ 위, 아래, 왼, 오른
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};
    static Player[] players;
    static int[][][] dirPriority; //idx, curDir, 0~4 우선순위 순서
    static int round;
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[1001][n][n];
        players = new Player[m + 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int idx = Integer.parseInt(st.nextToken());
                map[0][i][j] = idx;
                if (idx > 0) {
                    players[idx] = new Player(i, j, 0);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            players[i].dir = Integer.parseInt(st.nextToken());
        }
        dirPriority = new int[m + 1][5][4];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    dirPriority[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }
        round = 0;
        System.out.println(simulation());
    }

    static class Player {
        public int r, c;
        public int dir;
        public boolean isOut;

        Player(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            isOut = false;
        }
    }

    public static int simulation() {
        while (!flag && round < 1000) {
            round += 1;
            for (int i = 1; i <= m; i++) {
                if (!players[i].isOut) {
                    // 본인에게 인접한 상하좌우 4 칸 중 아무도 독점계약을 맺지 않은 칸으로 이동하고
                    int curDir = players[i].dir;
                    boolean isVacant = false;
                    int d = 0;
                    while (!isVacant && d < 4) {
                        int nextDir = dirPriority[i][curDir][d];
                        int nx = players[i].r + dx[nextDir];
                        int ny = players[i].c + dy[nextDir];
                        if (isInRange(nx, ny) && getOwnerNum(nx, ny) == 0) {
                            isVacant = true;
                            if (map[round][nx][ny] == 0) {
                                map[round][nx][ny] = i;
                                players[i].dir = nextDir;
                                players[i].r = nx;
                                players[i].c = ny;
                            } else {
                                players[i].isOut = true;
                            }
                        }
                        d += 1;
                    }
                    if (!isVacant) {
                        d = 0;
                        // 만약 그러한 칸이 없을 경우에는 인접한 4방향 중 본인이 독점계약한 땅으로 이동합니다.
                        boolean isEnd = false;
                        while (!isEnd && d < 4) {
                            int nextDir = dirPriority[i][curDir][d];
                            int nx = players[i].r + dx[nextDir];
                            int ny = players[i].c + dy[nextDir];
                            if (isInRange(nx, ny) && getOwnerNum(nx, ny) == i) {
                                isEnd = true;
                                if (map[round][nx][ny] == 0) {
                                    map[round][nx][ny] = i;
                                    players[i].dir = nextDir;
                                    players[i].r = nx;
                                    players[i].c = ny;
                                } else {
                                    players[i].isOut = true;
                                }
                            }
                            d += 1;
                        }

                    }
                }
            }
            flag = isEnd();
        }
        if (flag) {
            return round;
        } else {
            return -1;
        }

    }

    static boolean isEnd() {
        for (int i = 2; i <= m; i++) {
            if (!players[i].isOut) {
                return false;
            }
        }
        return true;
    }

    static boolean isInRange(int nx, int ny) {
        return (nx >= 0 && ny >= 0 && nx < n && ny < n);
    }

    public static int getOwnerNum(int x, int y) { //아무도 주인이 아니면 0반환
        for (int i = round - 1; i >= Math.max(round - k, 0); i--) {
            if (map[i][x][y] > 0) {
                return map[i][x][y];
            }
        }
        return 0;
    }


}
