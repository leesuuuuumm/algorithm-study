import java.util.*;

public class Main {
    static int N, M;
    static int min;
    static int[][] map;
    static int[][] cmap;
    static int[] direction;
    static boolean[][] visited;
    static ArrayList<CCTV> cctvs;
    static int[] dx = {-1, 0, 1, 0}; //시계방향으로 상우하좌
    static int[] dy = {0, 1, 0, -1}; //시계방향으로 상우하좌

   static class CCTV{
        int x;
        int y;
        int num;
        CCTV(int x, int y, int num){
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        min = Integer.MAX_VALUE; //최소 사각지대
        map = new int[N][M];
        cmap = new int[N][M]; //씨씨티비의 방향을 돌리면서 테스트할 맵
        cctvs = new ArrayList<>(); //씨씨티비의 위치와 번호를 저장
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                map[i][j] = sc.nextInt();
                if(map[i][j] != 0 && map[i][j] != 6){ //씨씨티비이면 저장
                    cctvs.add(new CCTV(i, j, map[i][j]));
                }
            }
        }
        direction = new int[cctvs.size()]; //씨씨티비를 어느 방향으로 돌렸는지 판단

        selectCCTV(0, cctvs.size());
        System.out.println(min);
    }

    public static void selectCCTV(int cnt, int c){
       //cnt는 방향을 선택한 씨씨티비의 수, c는 전체 씨씨티비의 수
        if(cnt == c){
            for(int i = 0; i < N; i++){
                System.arraycopy(map[i], 0, cmap[i], 0, M); //이차원 배열 복사
            }

            for(int i = 0; i < cctvs.size(); i++){
                changeDirection(cctvs.get(i), direction[i]); //선택한 방향에
            }

            countSafe();
            return;
        }

        for(int i = 0; i < 4; i++){ //상우하좌
            direction[cnt] = i; //for문을 돌면서 각각 상우하좌 한번씩 선택해서 넘겨줌
            selectCCTV(cnt+1, c);
        }
    }

    public static void changeDirection(CCTV cctv, int d){
       //선택된 씨씨티비와 회전할 방향
        int cNum = cctv.num; //씨씨티비의 번호

        if(cNum == 1){ //1번 씨씨티비면 한 방향만 타맥
            checkDirection(cctv.x, cctv.y, d); //상우하좌 중 하나의 방향만 탐색
        }
        else if(cNum == 2){ //2번이면 ㅡ자를 탐색
            if(d == 0 || d == 2){
                checkDirection(cctv.x, cctv.y, 0); //상
                checkDirection(cctv.x, cctv.y, 2); //하
            }
            else{
                checkDirection(cctv.x, cctv.y, 1); //우
                checkDirection(cctv.x, cctv.y, 3); //좌
            }
        }
        else if(cNum == 3){ //3번은 ㄴ모양
            if(d == 0){
                checkDirection(cctv.x, cctv.y, 0); //상
                checkDirection(cctv.x, cctv.y, 1); //우
            }
            else if(d == 1){
                checkDirection(cctv.x, cctv.y, 1); //우
                checkDirection(cctv.x, cctv.y, 2); //하
            }
            else if(d == 2){
                checkDirection(cctv.x, cctv.y, 2); //하
                checkDirection(cctv.x, cctv.y, 3); //좌
            }
            else if(d == 3){
                checkDirection(cctv.x, cctv.y, 0); //
                checkDirection(cctv.x, cctv.y, 3);
            }
        }
        else if(cNum == 4){ //4번은 ㅗ모양
            if(d == 0){
                checkDirection(cctv.x, cctv.y, 3); //좌
                checkDirection(cctv.x, cctv.y, 0); //상
                checkDirection(cctv.x, cctv.y, 1); //우
            }
            else if(d == 1){
                checkDirection(cctv.x, cctv.y, 0); //상
                checkDirection(cctv.x, cctv.y, 1); //우
                checkDirection(cctv.x, cctv.y, 2); //하
            }
            else if(d == 2){
                checkDirection(cctv.x, cctv.y, 1); //좌
                checkDirection(cctv.x, cctv.y, 2); //하
                checkDirection(cctv.x, cctv.y, 3); //우
            }
            else if(d == 3){
                checkDirection(cctv.x, cctv.y, 0); //상
                checkDirection(cctv.x, cctv.y, 2); //좌
                checkDirection(cctv.x, cctv.y, 3); //하
            }
        }
        else if(cNum == 5){ //5번은 +자
            checkDirection(cctv.x, cctv.y, 0);
            checkDirection(cctv.x, cctv.y, 1);
            checkDirection(cctv.x, cctv.y, 2);
            checkDirection(cctv.x, cctv.y, 3);
        }
    }

    static class Pair{
       int x;
       int y;
       Pair(int x, int y){
           this.x = x;
           this.y = y;
       }
    }

    public static void checkDirection(int X, int Y, int d){
       Queue<Pair> q = new LinkedList<>();

       q.add(new Pair(X,Y));
       visited[X][Y] = true;

       while(!q.isEmpty()){
           Pair now = q.poll();
           int nx = now.x + dx[d];
           int ny = now.y + dy[d];

           if(nx < 0 || nx >= N || ny < 0 || ny >= M || cmap[nx][ny] == 6)
               break;

           if(cmap[nx][ny] == 0){
               cmap[nx][ny] = -1;
               q.add(new Pair(nx, ny));
           }
           else {
               q.add(new Pair(nx, ny));
           }
       }
    }
    public static void countSafe(){
       //사각지대를 체크
        int count = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(cmap[i][j] == 0){
                    count++;
                }
            }
        }

        min = Math.min(min, count);
    }
}
