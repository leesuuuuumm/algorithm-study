import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 메이즈러너 {
    static int distanceSum;
    static int N, M, K;
    static int[][] map; //0:빈 칸, 1~9:벽, 10:출구,
    static int[][] perMap; //사용자 위치 , 개수
    static ArrayList<int[]> person;
    static int[] exit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        person = new ArrayList<>();
        perMap = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            person.add(new int[]{row, col});
            perMap[row][col] += 1;
        }
        st = new StringTokenizer(br.readLine());
        exit = new int[2];
        int exitRow = Integer.parseInt(st.nextToken());
        int exitCol = Integer.parseInt(st.nextToken());
        map[exitRow][exitCol] = 10;
        exit[0] = exitRow;
        exit[1] = exitCol;

        while (K > 0) {
            simulation();
            K--;
            if (person.isEmpty())
                break;
        }
        System.out.println(distanceSum);
        System.out.println(exit[0] + " " + exit[1]);
    }

    public static void simulation() {

        //이동
        move();

        if (person.isEmpty())
            return;
        //회전
        rotate();
    }

    public static Square getSmallSquare() {
        Square square = new Square(1, 1, 1);
        for (int sz = 2; sz <= N; sz++) {
            for (int x1 = 1; x1 <= N - sz + 1; x1++) {
                for (int y1 = 1; y1 <= N - sz + 1; y1++) {
                    int x2 = x1 + sz - 1;
                    int y2 = y1 + sz - 1;
                    if (!(x1 <= exit[0] && exit[0] <= x2 && y1 <= exit[1] && exit[1] <= y2)) {
                        continue;
                    }
                    boolean isTravelerIn = false;
                    for (int[] p : person) {
                        if (x1 <= p[0] && p[0] <= x2 && y1 <= p[1] && p[1] <= y2) {
                            isTravelerIn = true;
                        }
                    }
                    if (isTravelerIn) {
                        square = new Square(x1, y1, sz - 1);
                        return square;
                    }
                }
            }
        }
        return square;
    }
    public static void rotate(){
        // 1. 출구, 한 명 이상의 참가자 포함한 사각형 만들기
//        Square square=getSmallSquare();
        PriorityQueue<Square> que=new PriorityQueue<>();
        for (int[] p : person) {
            int bigRow = Math.max(exit[0], p[0]);
            int smallRow = Math.min(exit[0], p[0]);
            int bigCol = Math.max(exit[1], p[1]);
            int smallCol = Math.min(exit[1], p[1]);
            int rowDiff = bigRow - smallRow;
            int colDiff = bigCol - smallCol;
            int len = Math.max(rowDiff, colDiff);
            int leftTopRow = transOutOfRange(smallRow - (len - rowDiff));
            int leftTopCol = transOutOfRange(smallCol - (len - colDiff));
            if (leftTopRow + len <= N && leftTopCol + len <= N) {
                que.add(new Square(leftTopRow, leftTopCol, len));
            } else {
                int rightCol = Math.min(N, leftTopCol + len);
                int rightRow = Math.min(N, leftTopRow + len);
                if (rightCol - len >= 1 && rightRow - len >= 1) {
                    que.add(new Square(rightRow - len, rightCol - len, len));
                }
            }
        }
        // 2. 그 사각형 기준으로 exit, 참가자, 벽 모두 90도 시계방향으로 회전
        Square square=que.poll();
        int startRow = square.leftTopRow;
        int startCol = square.leftTopCol;
        int len = square.len;
        int[][] rotateMap = new int[len + 1][len + 1]; //map회전
        int[][] rotatePer = new int[len + 1][len + 1]; //참가자 회전
        for (int i = startRow; i <= startRow + len; i++) {
            for (int j = startCol; j <= startCol + len; j++) {
                rotateMap[i - startRow][j - startCol] = map[i][j];
                rotatePer[i - startRow][j - startCol] = perMap[i][j];
            }
        }
        rotate(rotateMap);
        rotate(rotatePer);
        for (int i = startRow; i <= startRow + len; i++) {
            for (int j = startCol; j <= startCol + len; j++) {
                if (rotateMap[i - startRow][j - startCol] >= 1 && rotateMap[i - startRow][j - startCol] <= 9) {
                    rotateMap[i - startRow][j - startCol]--;
                }
                map[i][j] = rotateMap[i - startRow][j - startCol];
                if (map[i][j] == 10) {
                    exit[0] = i;
                    exit[1] = j;
                }
                perMap[i][j] = rotatePer[i - startRow][j - startCol];
            }
        }
        person = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 0; k < perMap[i][j]; k++) {
                    person.add(new int[]{i, j});
                }
            }
        }
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;

        // reverse up and down
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;

            }
        }

        // reverse diagonally
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
    public static void move(){
        ArrayList<int[]> changePos = new ArrayList<>();
        for (int[] p : person) {
            if(p[0]==exit[0]&&p[1]==exit[1]){
                perMap[p[0]][p[1]] -= 1;
                continue;
            }
            PriorityQueue<Node> que = new PriorityQueue<>();
            for (int i = 0; i < 4; i++) {
                //상하좌우 이동
                int nx = p[0] + dx[i];
                int ny = p[1] + dy[i];
                if (isInRange(nx, ny)) {
                    int curDist=Math.abs(p[0] - exit[0]) + Math.abs(p[1] - exit[1]);
                    int newDist = Math.abs(nx - exit[0]) + Math.abs(ny - exit[1]);
                    if (newDist < curDist&&(map[nx][ny]==0||map[nx][ny]==10))//움직인 칸은 현재 머물러 있던 칸보다 출구까지의 최단 거리가 가까워야 합니다.
                        que.add(new Node(nx, ny, newDist, i));
                }
            }
            if (!que.isEmpty()) {//참가가가 움직일 수 없는 상황이라면, 움직이지 않습니다.
                Node n = que.poll();
                perMap[p[0]][p[1]] -= 1; //현재 위치는 1감소
                if (map[n.row][n.col] != 10) {//exit
                    changePos.add(new int[]{n.row, n.col});//위치 추가
                    perMap[n.row][n.col] += 1;//이동함
                }
                distanceSum += 1; //exit되면 추가 안함
            }else{
                changePos.add(new int[]{p[0],p[1]});
            }
        }
        person = changePos;
    }

    public static int transOutOfRange(int value) {
        return Math.max(value, 1);
    }

    public static boolean isInRange(int nx, int ny) {
        return nx >= 1 && ny >= 1 && nx <= N && ny <= N;
    }

    public static class Node implements Comparable<Node> {
        public int row;
        public int col;
        public int dist;
        public int dir;

        public Node(int row, int col, int dist, int dir) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.dir = dir;
        }

        @Override
        public int compareTo(Node o) {
            return this.dir - o.dir;
        }

    }

    public static class Square implements Comparable<Square> {
        public int leftTopRow;
        public int leftTopCol;
        public int len;

        public Square(int row, int col, int len) {
            this.leftTopRow = row;
            this.leftTopCol = col;
            this.len = len;
        }

        @Override
        public int compareTo(Square o) {
            if (this.len == o.len) {
                if (this.leftTopRow == o.leftTopRow) {
                    return this.leftTopCol - o.leftTopCol;
                }
                return this.leftTopRow - o.leftTopRow;
            }
            return this.len - o.len;
        }
    }
}
