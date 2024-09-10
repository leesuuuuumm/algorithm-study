import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 코드트리빵 {
    static int N, M;
    static int[][] map; //0: 빈 공간 1: 베이스캠프 2: 이동불가
    static int T;
    static Person[] persons;
    // ↑, ←, →, ↓ 의 우선 순위
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int count;
    static ArrayList<int[]> toBan;//밴할거

    public static class Person {
        public int r, c;
        public int sR, sC;
        public boolean isIn;
        public boolean isFin;

        public Person(int r, int c, int sR, int sC, boolean isIn, boolean isFin) {
            this.r = r;
            this.c = c;
            this.sR = sR;
            this.sC = sC;
            this.isIn = isIn;
            this.isFin = isFin;
        }
    }

    public static class Node implements Comparable<Node> {
        public int distance;
        public int dir;
        public int r, c;

        Node(int distance, int dir, int r, int c) {
            this.distance = distance;
            this.dir = dir;
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            if(this.distance==o.distance) {
                if (this.r == o.r)
                    return this.c - o.c;
                return this.r - o.r;
            }
            return this.distance-o.distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        persons = new Person[M + 1];
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        persons[0] = new Person(0, 0, 0, 0, false, false);
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            persons[i] = new Person(-1, -1, x, y, false, false);
        }
        count = 0;
        T = 0;
        while (count < M) {
            T += 1;
            toBan = new ArrayList<>();
            goStore();
            makeBan();
            startFromBaseCamp();
        }
        System.out.println(T);
    }

    public static void goStore() {
        // 격자에 있는 사람들 모두가 본인이 가고 싶은 편의점 방향을 향해서 1 칸 움직입니다.
        // 최단거리로 움직이며 최단 거리로 움직이는 방법이 여러가지라면 ↑, ←, →, ↓ 의 우선 순위로 움직이게 됩니다.
        // 여기서 최단거리라 함은 상하좌우 인접한 칸 중 이동가능한 칸으로만 이동하여 도달하기까지 거쳐야 하는 칸의 수가 최소가 되는 거리를 뜻합니다
        int limit=T;
        if(T>M){
            limit=M+1;
        }
        for (int i = 1; i <limit; i++) {
            Person person = persons[i];
            //안에 있고, 끝나지 않았으면
            if (person.isIn && !person.isFin) {
                int dir = bfs(person.r, person.c, person.sR, person.sC);
                //이동
                person.r += dx[dir];
                person.c += dy[dir];
                if (person.r == person.sR && person.c == person.sC) {//도착
                    person.isFin = true;
                    person.isIn = false;
                    toBan.add(new int[]{person.r, person.c});
                    count+=1;
                }
            }
        }
    }

    public static int bfs(int startR, int startC, int objR, int objC) {
        Queue<Node> que = new ArrayDeque<>();
        boolean[][] visited=new boolean[N+1][N+1];
        visited[startR][startC]=true;
        for (int i = 0; i < 4; i++) {
            int nx = startR + dx[i];
            int ny = startC + dy[i];
            if (canGo(nx, ny)) {
                if (nx == objR && ny == objC) {
                    return i;
                }
                visited[nx][ny]=true;
                que.add(new Node(1, i, nx, ny));
            }
        }

        while (!que.isEmpty()) {
            Node n = que.poll();
            if (n.r == objR && n.c == objC) {
                return n.dir;
            }
            for (int i = 0; i < 4; i++) {
                int nx = n.r + dx[i];
                int ny = n.c + dy[i];
                if (canGo(nx, ny)&&!visited[nx][ny]) {
                    visited[nx][ny]=true;
                    que.add(new Node(1, n.dir, nx, ny));
                }
            }
        }
        return 0;
    }

    public static int[] findBaseCamp(int startR, int startC) {
        Queue<Node> que = new ArrayDeque<>();
        int findDist = Integer.MAX_VALUE;
        que.add(new Node(0, 1, startR, startC));
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean isFind=false;
        boolean[][] visited=new boolean[N+1][N+1];
        visited[startR][startC]=true;

        while (!que.isEmpty()) {
            Node n = que.poll();
            if(isFind&&n.distance>findDist){
                Node no=pq.poll();
                return new int[]{no.r,no.c};
            }
            if (map[n.r][n.c] == 1) {
                pq.add(new Node(n.distance, n.dir, n.r, n.c));
                isFind=true;
                findDist=Math.min(n.distance,findDist);
            }
            for (int i = 0; i < 4; i++) {
                int nx = n.r + dx[i];
                int ny = n.c + dy[i];
                if (canGo(nx, ny)&&!visited[nx][ny]) {
                    visited[nx][ny]=true;
                    que.add(new Node(n.distance+1, n.dir, nx, ny));
                }
            }
        }
        if(!pq.isEmpty()){
            Node no=pq.poll();
            return new int[]{no.r,no.c};
        }else{
            return new int[]{0,0};
        }
    }

    public static void makeBan() {
        for (int[] ban : toBan) {
            int x = ban[0];
            int y = ban[1];
            map[x][y] = 2;
        }
    }

    public static void startFromBaseCamp() {
        //현재 시간이 t분이고 t ≤ m를 만족한다면, t번 사람은 자신이 가고 싶은 편의점과 가장 가까이 있는 베이스 캠프에 들어갑니다.
        // 여기서 가장 가까이에 있다는 뜻 역시 1에서와 같이 최단거리에 해당하는 곳을 의미합니다.
        // 가장 가까운 베이스캠프가 여러 가지인 경우에는 그 중 행이 작은 베이스캠프, 행이 같다면 열이 작은 베이스 캠프로 들어갑니다.
        // t번 사람이 베이스 캠프로 이동하는 데에는 시간이 전혀 소요되지 않습니다.
        if (T <= M) {
            Person person = persons[T];
            int[] basecamp=findBaseCamp(person.sR,person.sC);
            person.r=basecamp[0];
            person.c=basecamp[1];
            map[person.r][person.c]=2;
            person.isIn=true;
        }
    }

    static boolean isInRange(int nx, int ny) {
        return (nx >= 1 && nx <= N && ny >= 1 && ny <= N);
    }

    static boolean canGo(int nx, int ny) {
        return (isInRange(nx, ny) && map[nx][ny] != 2);
    }

}
