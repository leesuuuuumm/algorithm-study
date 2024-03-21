package boj;

public class Main_21608_상어초등학교 {

    static class Location {
        int r;
        int c;

        public Location(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N;
    static int[][] map;
    static int[][] friends;

    public static void main(String[] args) throws Exception {
        N = read();
        map = new int[N][N];
        friends = new int[(int) Math.pow(N, 2) + 1][4];

        for (int i = 0; i < (int) Math.pow(N, 2); i++) {
            int cur = read();

            for (int j = 0; j < 4; j++) {
                friends[cur][j] = read();
            }

            findPosition(cur);
        }

        System.out.println(calculatePoint());
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    private static void findPosition(int cur) {
        int maxEmpty = -1;
        int maxFriends = -1;
        Location max = null;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] != 0) continue;

                int emptyCount = 0;
                int friendsCount = 0;

                for (int dir = 0; dir < 4; dir++) {
                    int nr = r + dr[dir];
                    int nc = c + dc[dir];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                        continue;
                    }
                    if (map[nr][nc] == 0) {
                        emptyCount++;
                        continue;
                    }
                    if (map[nr][nc] == friends[cur][0] || map[nr][nc] == friends[cur][1] || map[nr][nc] == friends[cur][2] || map[nr][nc] == friends[cur][3]) {
                        friendsCount++;
                    }
                }

                if (friendsCount > maxFriends) {
                    maxFriends = friendsCount;
                    maxEmpty = emptyCount;
                    max = new Location(r, c);
                } else if (friendsCount == maxFriends) {
                    if (emptyCount > maxEmpty) {
                        maxEmpty = emptyCount;
                        max = new Location(r, c);
                    }
                }
            }
        }
        map[max.r][max.c] = cur;
    }

    private static int calculatePoint() {
        int point = 0;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int cur = map[r][c];
                int count = 0;

                for (int dir = 0; dir < 4; dir++) {
                    int nr = r + dr[dir];
                    int nc = c + dc[dir];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                        continue;
                    }
                    if (map[nr][nc] == friends[cur][0] || map[nr][nc] == friends[cur][1] || map[nr][nc] == friends[cur][2] || map[nr][nc] == friends[cur][3]) {
                        count++;
                    }
                }

                if (count == 1) point += 1;
                else if (count == 2) point += 10;
                else if (count == 3) point += 100;
                else if (count == 4) point += 1000;
            }
        }

        return point;
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}
