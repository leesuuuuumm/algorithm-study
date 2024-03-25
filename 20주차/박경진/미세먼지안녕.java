import java.util.*;

public class Main {
    static int R, C, T;
    static int[][] map, cmap;
    static int[] airX;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    class Pair{
        int x;
        int y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        T = sc.nextInt();
        map = new int[R][C];
        airX = new int[2];

        int a = 0;
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                map[i][j] = sc.nextInt(); //-1이면 공기청정기
                if(map[i][j] == -1){
                    airX[a] = i;
                    a++;
                }
            }
        }

        for(int i = 0; i < T; i++){
            startRefresh();
        }

        int answer = 0;
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(map[i][j] == -1) continue;
                answer += map[i][j];
            }
        }

        System.out.println(answer);
    }

    public static void startRefresh(){
        dustDiffusion();
        map = cmap;
        airClear();
    }

    public static void dustDiffusion(){
        cmap = new int[50][50];
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(map[i][j] == -1){ //공기청정기이면
                    cmap[i][j] = -1;
                    continue;
                }

                cmap[i][j] += map[i][j];
                for(int d = 0; d < 4; d++){
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if(nx < 0 || nx >= R || ny < 0 || ny >= C)
                        continue;
                    if(map[nx][ny] == -1)
                        continue;

                    cmap[nx][ny] += (map[i][j] / 5);
                    cmap[i][j] -= (map[i][j] / 5);
                }
            }
        }
    }

    public static void airClear(){
        int up = airX[0];
        int down = airX[1];

        for(int i = up - 1; i > 0; i--) map[i][0] = map[i-1][0];
        for(int i = 0; i < C - 1; i++) map[0][i] = map[0][i+1];
        for(int i = 0; i < up; i++) map[i][C-1] = map[i+1][C-1];
        for(int i = C - 1; i > 1; i--) map[up][i] = map[up][i-1];
        for(int i = down + 1; i < R - 1; i++) map[i][0] = map[i+1][0];
        for(int i = 0; i < C - 1; i++) map[R-1][i] = map[R-1][i+1];
        for(int i = R - 1; i > down; i--) map[i][C-1] = map[i-1][C-1];
        for(int i = C - 1; i > 1; i--) map[down][i] = map[down][i-1];

        map[up][1] = 0;
        map[down][1] = 0;
    }
}
