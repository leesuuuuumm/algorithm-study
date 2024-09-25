import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 나무타이쿤 {
    static ArrayList<int[]> tonic = new ArrayList<>();
    static int[][][] map;
    static boolean[][] isIn;
    static int n, t;
    static int round;
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, 1, 1, 0, -1, -1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        map = new int[t+1][n][n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[0][i][j]=Integer.parseInt(st.nextToken());
            }
        }
        int[][] moveArr=new int[t][2];
        for(int i=0;i<t;i++){
            st = new StringTokenizer(br.readLine());
            moveArr[i][0]=Integer.parseInt(st.nextToken());
            moveArr[i][1]=Integer.parseInt(st.nextToken());
        }

        for(int i=n-2;i<=n-1;i++){
            for(int j=0;j<=1;j++){
                tonic.add(new int[]{i,j});
            }
        }
        round=0;
        while(round<t){
            move(moveArr[round][0],moveArr[round][1]);
            grow();
            extraGrow();
            cutAndMove();
            round++;
        }
        int sum=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                sum+=map[t][i][j];
            }
        }
        System.out.println(sum);
    }
    static void move(int dir, int num){
        isIn=new boolean[n][n];
        for(int[] t:tonic){
            int nx=t[0];
            int ny=t[1];
            for(int i=1;i<=num;i++){
                nx=(nx+dx[dir]+n)%n;
                ny=(ny+dy[dir]+n)%n;
            }
            t[0]=nx;
            t[1]=ny;
            isIn[nx][ny]=true;
        }
    }
    static void grow(){
        for(int[] t:tonic){
            map[round][t[0]][t[1]]+=1;
        }
    }
    static void extraGrow(){
        for(int[] t: tonic){
            for(int i=2;i<=8;i+=2){
                int nx=t[0]+dx[i];
                int ny=t[1]+dy[i];
                if(isInRange(nx,ny)){
                    if(map[round][nx][ny]>=1){
                        map[round][t[0]][t[1]]+=1;
                    }
                }
            }
        }
    }
    static void cutAndMove(){
        tonic=new ArrayList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!isIn[i][j]&&map[round][i][j]>=2){
                    map[round+1][i][j]=map[round][i][j]-2;
                    tonic.add(new int[]{i,j});
                }else{
                    map[round+1][i][j]=map[round][i][j];
                }
            }
        }
    }
    static boolean isInRange(int nx, int ny){
        return (nx>=0&&ny>=0&&nx<n&&ny<n);
    }

}
