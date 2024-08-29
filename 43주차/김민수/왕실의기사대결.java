import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 왕실의기사대결 {
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};
    static int[][] map, knight;
    static boolean[][] trap, wall;
    static int l, n, q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        int[] originKnightValue = new int[n + 1];
        map = new int[l + 1][l + 1];
        trap = new boolean[l + 1][l + 1];
        wall = new boolean[l + 1][l + 1];
        knight = new int[n + 1][6]; //0: row, 1: col, 2: h 3:w 4:체력 5: out 여부 (0:in, 1:out)

        for (int i = 1; i <= l; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= l; j++) {
                int info = Integer.parseInt(st.nextToken());
                if (info == 1) {
                    trap[i][j] = true;
                } else if (info == 2) {
                    wall[i][j] = true;
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                knight[i][j] = Integer.parseInt(st.nextToken());
            }
            int r = knight[i][0];
            int c = knight[i][1];
            for (int h = 0; h < knight[i][2]; h++) {
                for (int w = 0; w < knight[i][3]; w++) {
                    map[r + h][c + w] = i;
                }
            }
            originKnightValue[i] = knight[i][4];
        }
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());

            int idx = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            if(knight[idx][5]==0) {
                moveKnight(idx, dir);
            }

        }

        int originSum = 0;
        int afterSum = 0;
        for (int i = 0; i <= n; i++) {
            if (knight[i][5] == 0) {
                originSum += originKnightValue[i];
                afterSum += knight[i][4];
            }
        }
        System.out.println(originSum - afterSum);

    }

    public static void moveKnight(int idx, int dir) {
        Queue<Knight> que = new ArrayDeque<>(); //미뤄질 나이프들
        Queue<Knight> store = new ArrayDeque<>(); //미뤄질 나이프들
        store.add(new Knight(knight[idx], idx, dir, true));
        que.add(new Knight(knight[idx], idx, dir, true));
        while (!que.isEmpty()) {
            Knight k = que.poll();
            int r = k.info[0] + dr[dir];
            int c = k.info[1] + dc[dir];
            Set<Integer> set = new HashSet<>();
            for (int h = 0; h < k.info[2]; h++) {
                for (int w = 0; w < k.info[3]; w++) {
                    if (isInRange(r + h, c + w)) {
                        if (map[r + h][c + w] != 0) {//이동하려는 위치에 다른 기사가 있음
                            int moveIdx = map[r + h][c + w];
                            if (!set.contains(moveIdx)&&moveIdx!=k.idx) {
                                set.add(moveIdx);
                            }
                        }
                    } else {
                        return;
                    }
                }
            }
            for(int moveIdx:set) {
                que.add(new Knight(knight[moveIdx], moveIdx, dir, false));
                store.add(new Knight(knight[moveIdx], moveIdx, dir, false));
            }
        }

        calculateDamage(store);

    }

    public static boolean isInRange(int row, int col) {
        if (row < 1 || row > l || col < 1 || col > l)
            return false;
        if (wall[row][col])
            return false;
        return true;
    }

    public static void calculateDamage(Queue<Knight> store) { //밀춰진 후 계산
        while (!store.isEmpty()) {
            Knight k = store.poll();
            int r = k.info[0] + dr[k.dir];
            int c = k.info[1] + dc[k.dir];
            if(!k.notDamage) {
                for (int h = 0; h < k.info[2]; h++) {
                    for (int w = 0; w < k.info[3]; w++) {
                        if (trap[r + h][c + w]) {//이동하려는 위치에 함정이 있음
                            if (knight[k.idx][4] >1) {
                                knight[k.idx][4]--;
                            } else {
                                knight[k.idx][5] = 1;
                            }
                        }
                    }
                }
            }
            for (int h = 0; h < k.info[2]; h++) {
                for (int w = 0; w < k.info[3]; w++) {
                    if(map[k.info[0]+h][k.info[1]+w]==k.idx)
                        map[k.info[0] + h][k.info[1] + w] = 0;
                }
            }
            if(knight[k.idx][5]==0){
                for (int h = 0; h < k.info[2]; h++) {
                    for (int w = 0; w < k.info[3]; w++) {
                        map[r+h][c+w]=k.idx;
                    }
                }
                knight[k.idx][0]+=dr[k.dir];
                knight[k.idx][1]+=dc[k.dir];
            }

        }

    }

    public static class Knight {
        public int[] info;
        public int idx;
        public int dir;
        public boolean notDamage;

        public Knight(int[] info, int idx, int dir, boolean notDamage) {
            this.info = info;
            this.idx = idx;
            this.dir = dir;
            this.notDamage=notDamage;
        }
    }


}
