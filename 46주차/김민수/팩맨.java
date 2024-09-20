import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 팩맨 {
    static ArrayList<Monster> aliveMonster;
    static ArrayList<Monster> copyMonster;
    static ArrayList<Monster> deadMonster;
    static boolean[][] visited;
    static PriorityQueue<Step> que ;

    static int[] packMan;
    static ArrayList<Integer>[][] deadMonsterMap; // 시체
    static ArrayList<Integer>[][] aliveMonsterMap; //살아있는 몬스터 맵 (방향)
    static int round;
    //↑, ↖, ←, ↙, ↓, ↘, →, ↗
    static int[] mDx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] mDy = {0, 0, -1, -1, -1, 0, 1, 1, 1,};

    //↑,←,↓,→,
    static int[] pDx = {-1, 0, 1, 0};
    static int[] pDy = {0, -1, 0, 1};

    public static class Monster {
        public int r, c;
        public int d;

        Monster(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        packMan = new int[2];

        st = new StringTokenizer(br.readLine());
        packMan[0] = Integer.parseInt(st.nextToken());
        packMan[1] = Integer.parseInt(st.nextToken());
        aliveMonster = new ArrayList<>();
        deadMonster = new ArrayList<>();
        aliveMonsterMap = new ArrayList[5][5];
        deadMonsterMap = new ArrayList[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                aliveMonsterMap[i][j] = new ArrayList<>();
                deadMonsterMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            aliveMonster.add(new Monster(r, c, d));
            aliveMonsterMap[r][c].add(d);
        }
        round = 1;
        while (round <= t) {
            storeCopy();
            moveMonster();
            movePacMan();
            deleteDead();
            completeCopy();
            round += 1;
        }
        System.out.println(aliveMonster.size());
    }

    public static void storeCopy() {
        copyMonster = new ArrayList<>();
        for (int i = 0; i < aliveMonster.size(); i++) {
            Monster m = aliveMonster.get(i);
            copyMonster.add(new Monster(m.r, m.c, m.d));
        }

    }

    public static void moveMonster() {
        // 몬스터는 현재 자신이 가진 방향대로 한 칸 이동합니다.
        // 이때 움직이려는 칸에 몬스터 시체가 있거나,팩맨이 있는 경우거나 격자를 벗어나는 방향일 경우에는 반시계 방향으로 45도를 회전한 뒤 해당 방향으로 갈 수 있는지 판단합니다.
        // 만약 갈 수 없다면, 가능할 때까지 반시계 방향으로 45도씩 회전하며 해당 방향으로 갈 수 있는지를 확인합니다.
        // 만약 8 방향을 다 돌았는데도 불구하고, 모두 움직일 수 없었다면 해당 몬스터는 움직이지 않습니다.
        for (int i = 0; i < aliveMonster.size(); i++) {
            Monster m = aliveMonster.get(i);
            for (int j = 0; j < 9; j++) {
                int dir = (m.d + j) % 9;
                if (dir == 0)
                    continue;
                int nx = m.r + mDx[dir];
                int ny = m.c + mDy[dir];
                if (canGo(nx, ny)) {
                    for (int d = 0; d < aliveMonsterMap[m.r][m.c].size(); d++) {
                        Integer dd=aliveMonsterMap[m.r][m.c].get(d);
                        if (dd == m.d) {
                            aliveMonsterMap[m.r][m.c].remove(Integer.valueOf(m.d));
                        }
                    }
                    m.r = nx;
                    m.c = ny;
                    m.d = dir;
                    aliveMonsterMap[m.r][m.c].add(m.d);
                    break;
                }
            }
        }

    }
    public static boolean[][] copy(boolean[][] visited){
        boolean[][] newVisited=new boolean[5][5];
        for(int i=1;i<=4;i++){
            for(int j=1;j<=4;j++){
                newVisited[i][j]=visited[i][j];
            }
        }
        return newVisited;
    }
    static void dfs(int x, int y, int depth, int count, boolean[][] visited, List<int[]> path, int idx) {
        if (depth == 3) {
            // 경로 평가 및 업데이트
            que.add(new Step(path.get(0)[0],path.get(0)[1],path.get(1)[0],path.get(1)[1],x,y,count,idx));
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + pDx[i];
            int ny = y + pDy[i];
            if (isInRange(nx, ny)) {
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    path.add(new int[]{nx, ny});
                    dfs(nx, ny, depth + 1, count + aliveMonsterMap[nx][ny].size(), visited, path, idx+1);
                    visited[nx][ny] = false;
                    path.remove(path.size() - 1);
                } else {
                    dfs(nx, ny, depth + 1, count, visited, path, idx+1);
                }
            }
        }
    }


    public static void movePacMan() {
        // 팩맨의 이동은 총 3칸을 이동하게 되는데, 각 이동마다 상하좌우의 선택지를 가지게 됩니다.
        // 총 4가지의 방향을 3칸 이동하기 때문에 총 64개의 이동 방법이 존재하는데, 이 중 몬스터를 가장 많이 먹을 수 있는 방향으로 움직이게 됩니다.
        // 만약 가장 많이 먹을 수 있는 방향이 여러개라면 상-좌-하-우의 우선순위를 가지며 우선순위가 높은 순서대로 배열
        // 이동하는 과정에 격자 바깥을 나가는 경우는 고려하지 않습니다.

        que = new PriorityQueue<>();
        visited=new boolean[5][5];

        dfs(packMan[0], packMan[1], 0, 0, visited, new ArrayList<>(),1);
        // 이때 이동할 때 이동하는 칸에 있는 몬스터는 모두 먹어치운 뒤, 그 자리에 몬스터의 시체를 남깁니다.
        // 이때 팩맨은 알은 먹지 않으며, 움직이기 전에 함께 있었던 몬스터도 먹지 않습니다.
        // 즉, 이동하는 과정에 있는 몬스터만 먹습니다.
        Step s = que.poll();
        packMan[0] = s.thirdR;
        packMan[1] = s.thirdC;

        ArrayList<Integer> temp = new ArrayList<>(aliveMonsterMap[s.firstR][s.firstC]);
        if(!temp.isEmpty()) {
            for(int k=0;k<temp.size();k++) {
                deadMonster.add(new Monster(s.firstR, s.firstC, round));
                deadMonsterMap[s.firstR][s.firstC].add(round);
            }
            aliveMonsterMap[s.firstR][s.firstC].clear();
        }

        temp = new ArrayList<>(aliveMonsterMap[s.secondR][s.secondC]);
        if(!temp.isEmpty()) {
            for(int k=0;k<temp.size();k++) {
                deadMonster.add(new Monster(s.secondR, s.secondC, round));
                deadMonsterMap[s.secondR][s.secondC].add(round);

            }
            aliveMonsterMap[s.secondR][s.secondC].clear();
        }

        temp = new ArrayList<>(aliveMonsterMap[s.thirdR][s.thirdC]);
        if(!temp.isEmpty()) {
            for(int k=0;k<temp.size();k++) {
                deadMonster.add(new Monster(s.thirdR, s.thirdC, round));
                deadMonsterMap[s.thirdR][s.thirdC].add(round);

            }
            aliveMonsterMap[s.thirdR][s.thirdC].clear();
        }

        aliveMonster = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                if (!aliveMonsterMap[i][j].isEmpty()) {
                    for (int dir : aliveMonsterMap[i][j]) {
                        aliveMonster.add(new Monster(i, j, dir));
                    }
                }
            }
        }
    }

    public static class Step implements Comparable<Step> {
        public int firstR, firstC, secondR, secondC, thirdR, thirdC;
        public int count;
        public int idx;

        Step(int firstR, int firstC, int secondR, int secondC, int thirdR, int thirdC, int count, int idx) {
            this.firstR = firstR;
            this.firstC = firstC;
            this.secondR = secondR;
            this.secondC = secondC;
            this.thirdR = thirdR;
            this.thirdC = thirdC;
            this.count = count;
            this.idx=idx;
        }

        @Override
        public int compareTo(Step o) {
            if(o.count==this.count){
                return this.idx-o.idx;
            }
            return o.count - this.count;
        }
    }

    public static void deleteDead() {
        //몬스터의 시체는 총 2턴동안만 유지됩니다.
        // 즉, 시체가 생기고 나면 시체가 소멸되기 까지는 총 두 턴을 필요로 합니다.
        ArrayList<Monster> removeMonster = new ArrayList<>();
        for (int i = 0; i < deadMonster.size(); i++) {
            Monster m = deadMonster.get(i);
            if (round>= m.d+2) {
                removeMonster.add(m);
            }
        }
        if (!removeMonster.isEmpty()) {
            deadMonster.removeAll(removeMonster);
            for(int k=0;k<removeMonster.size();k++){
                Monster m=removeMonster.get(k);
                for(int j=0;j<deadMonsterMap[m.r][m.c].size();j++) {
                    Integer dd = deadMonsterMap[m.r][m.c].get(j);
                    if (dd == m.d) {
                        deadMonsterMap[m.r][m.c].remove(Integer.valueOf(m.d));
                    }
                }
            }
        }
    }

    public static void completeCopy() {
        for (Monster m : copyMonster) {
            aliveMonster.add(new Monster(m.r, m.c, m.d));
            aliveMonsterMap[m.r][m.c].add(m.d);
        }
    }

    public static boolean canGo(int nx, int ny) {
        if (isInRange(nx, ny)) {
            if (!(nx == packMan[0] && ny == packMan[1])) {
                if (deadMonsterMap[nx][ny].size() == 0)
                    return true;
            }
        }
        return false;
    }

    public static boolean isInRange(int nx, int ny) {
        return nx >= 1 && ny >= 1 && nx <= 4 && ny <= 4;
    }
}
