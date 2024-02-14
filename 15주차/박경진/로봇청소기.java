import java.util.Scanner;

public class Main {
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0}; //북동남서
    static int[] dy = {0, 1, 0, -1}; //북동남서
    static int N,M;
    static int clean;


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); //행, 방의 세로 길이
        M = sc.nextInt(); //열, 방의 가로 길이
        map = new int[N][M];
        int r = sc.nextInt(); //청소기의 현재 행 위치
        int c = sc.nextInt(); //청소기의 현재 열 위치
        int d = sc.nextInt(); //청소기가 현재 바라보고 있는 방향, (0,북),(1,동),(2,남),(3,서)

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                map[i][j] = sc.nextInt(); //0은 청소되지 않은 칸, 1은 벽, 2는 청소완료
            }
        }

        clean = 1;
        cleanRoom(r,c,d);
        System.out.println(clean);
    }

    static void cleanRoom(int x, int y, int v){
        map[x][y] = 2; //현재 위치 청소

        for(int i = 0; i < 4; i++){
            v = (v + 3) % 4;

            int nx = x + dx[v];
            int ny = y + dy[v];
            if(nx > N-1 || nx < 0 || ny > M-1 || ny < 0){
                continue;
            }

            if(map[nx][ny] == 0){
                clean++;
                cleanRoom(nx,ny,v);
                return;
            }
        }

        //주변 4칸 중 청소되지 않은 칸이 없다면
        int back = (v + 2) % 4;
        int bx = x + dx[back];
        int by = y + dy[back];

        if(!(bx > N-1 || bx < 0 || by > M-1 || by < 0 || (map[bx][by] == 1))){
            cleanRoom(bx,by,v);
        }
    }
}
