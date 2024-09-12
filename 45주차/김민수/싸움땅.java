import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 싸움땅 {
    static int n, m, k;
    // ↑, →, ↓, ←
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static ArrayList<Integer>[][] gunMap;
    static int[][] playerMap;
    static ArrayList<Player> players;
    static int[] point;

    public static class Player {
        public int x, y;
        public int d;
        public int s;
        public int gun;

        public Player(int x, int y, int d, int s, int gun) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.s = s;
            this.gun = gun;
        }

        public boolean doHaveGun() {
            return (gun > 0);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        gunMap = new ArrayList[n + 1][n + 1];
        players = new ArrayList<>();
        playerMap = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int gun = Integer.parseInt(st.nextToken());
                gunMap[i][j] = new ArrayList<>();
                if (gun > 0)
                    gunMap[i][j].add(gun);
            }
        }
        players.add(new Player(0, 0, 0, 0, 0));
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            players.add(new Player(x, y, d, s, 0));
            playerMap[x][y] = i;
        }
        point = new int[m + 1];

        while (k > 0) {
            for (int i = 1; i <= m; i++) {
                move(i);
            }
            k--;
        }
        for (int i = 1; i <= m; i++) {
            System.out.print(point[i] + " ");
        }
    }

    public static boolean doHaveGun(int x, int y) {
        return !gunMap[x][y].isEmpty();
    }

    public static int getMaxGun(int x, int y) {
        Collections.sort(gunMap[x][y], new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        int maxGun = gunMap[x][y].get(0);
        gunMap[x][y].remove(0);
        return maxGun;
    }

    public static void addGun(int x, int y, int val) {
        gunMap[x][y].add(val);
    }

    public static void move(int idx) {
        Player player = players.get(idx);
        int curX = player.x;
        int curY = player.y;
        playerMap[curX][curY] = 0;
        int dir = players.get(idx).d;
        int nx = curX + dx[dir];
        int ny = curY + dy[dir];
        //향하고 있는 방향대로 한 칸만큼 이동합니다.
        // 만약 해당 방향으로 나갈 때 격자를 벗어나는 경우에는
        // 정반대 방향으로 방향을 바꾸어서 1만큼 이동합니다.
        if (!isInRange(nx, ny)) {
            dir = (dir + 2) % 4;
            nx = curX + dx[dir];
            ny = curY + dy[dir];
        }
        //이동하기
        player.d = dir;
        player.x = nx;
        player.y = ny;

        if (playerMap[nx][ny] == 0) {
            playerMap[nx][ny] = idx;
            //만약 이동한 방향에 플레이어가 없다면 해당 칸에 총이 있는지 확인합니다.
            if (doHaveGun(nx, ny)) {// 총이 있는 경우, 해당 플레이어는 총을 획득합니다.
                if (player.doHaveGun()) {
                    // 플레이어가 이미 총을 가지고 있는 경우에는 놓여있는 총들과 플레이어가 가지고 있는 총
                    // 가운데 공격력이 더 쎈 총을 획득하고,
                    // 나머지 총들은 해당 격자에 둡니다.
                    int maxGun = getMaxGun(nx, ny);
                    int max = Math.max(player.gun, maxGun);
                    int min = Math.min(player.gun, maxGun);
                    player.gun = max;
                    addGun(nx, ny, min);
                } else {
                    player.gun = getMaxGun(nx, ny);
                }
            }

        } else {
            int[] result = fight(idx, playerMap[nx][ny]);
            int winIdx = result[0];
            int loseIdx = result[1];
            Player winner = players.get(winIdx);
            Player loser = players.get(loseIdx);

            // 이긴 플레이어는 각 플레이어의 초기 능력치와 가지고 있는 총의 공격력의 합의 차이만큼을 포인트로 획득하게 됩니다
            point[winIdx] += (winner.s + winner.gun) - (loser.s + loser.gun);
            // 진 플레이어는 본인이 가지고 있는 총을 해당 격자에 내려놓고,
            if (loser.doHaveGun()) {
                int loserGun = loser.gun;
                addGun(nx, ny, loserGun);
                loser.gun = 0;
            }

            //해당 플레이어가 원래 가지고 있던 방향대로 한 칸 이동합니다.
            //만약 이동하려는 칸에 다른 플레이어가 있거나 격자 범위 밖인 경우에는 오른쪽으로 90도씩 회전하여 빈 칸이 보이는 순간 이동합니다.
            int nextX = loser.x + dx[loser.d];
            int nextY = loser.y + dy[loser.d];
            while (!isValid(nextX, nextY)) {
                loser.d = (loser.d + 1) % 4;
                nextX = loser.x + dx[loser.d];
                nextY = loser.y + dy[loser.d];
            }
            // 만약 해당 칸에 총이 있다면, 해당 플레이어는 가장 공격력이 높은 총을 획득하고 나머지 총들은 해당 격자에 내려 놓습니다.
            loser.x = nextX;
            loser.y = nextY;
            if (doHaveGun(nextX, nextY)) {
                loser.gun = getMaxGun(nextX, nextY);
            }
            playerMap[nextX][nextY]=loseIdx;

            // 이긴 플레이어는 승리한 칸에 떨어져 있는 총들과 원래 들고 있던 총 중 가장 공격력이 높은 총을 획득하고,
            // 나머지 총들은 해당 격자에 내려 놓습니다.
            if (doHaveGun(nx, ny)) {
                if(winner.doHaveGun()) {
                    int maxGun = getMaxGun(nx, ny);
                    int max = Math.max(winner.gun, maxGun);
                    int min = Math.min(winner.gun, maxGun);
                    winner.gun = max;
                    addGun(nx, ny, min);
                }else{
                    winner.gun=getMaxGun(nx,ny);
                }
            }
            playerMap[nx][ny] = winIdx;
        }

    }

    public static int[] fight(int aIdx, int bIdx) {
        // 만약 이동한 방향에 플레이어가 있는 경우에는 두 플레이어가 싸우게 됩니다.
        // 해당 플레이어의 초기 능력치와 가지고 있는 총의 공격력의 합을 비교하여 더 큰 플레이어가 이기게 됩니다.
        // 만일 이 수치가 같은 경우에는 플레이어의 초기 능력치가 높은 플레이어가 승리하게 됩니다.
        Player aPlayer = players.get(aIdx);
        Player bPlayer = players.get(bIdx);
        int aSum = aPlayer.s + aPlayer.gun;
        int bSum = bPlayer.s + bPlayer.gun;
        int winIdx = 0;
        int loseIdx = 0;
        if (aSum > bSum) {
            winIdx = aIdx;
            loseIdx = bIdx;
        } else if (bSum > aSum) {
            winIdx = bIdx;
            loseIdx = aIdx;
        } else {
            if (aPlayer.s > bPlayer.s) {
                winIdx = aIdx;
                loseIdx = bIdx;
            } else {
                winIdx = bIdx;
                loseIdx = aIdx;
            }
        }

        return new int[]{winIdx, loseIdx};
    }

    public static boolean isInRange(int nx, int ny) {
        return (nx >= 1 && nx <= n && ny >= 1 && ny <= n);
    }

    public static boolean isValid(int nx, int ny){
        return isInRange(nx,ny)&&playerMap[nx][ny]==0;
    }

}
