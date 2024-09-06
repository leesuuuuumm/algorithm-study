

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _14500 {
    static int result, sum;
    static int[][] map;
    static boolean[][] visited;
    static int N, M;
    static int[] dx={-1, 0, 1, 0}; //위, 왼, 아래, 오른
    static int[] dy={0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        map=new int[N][M];
        visited=new boolean[N][M];
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                visited[i][j]=true;
                dfs(i, j, 1, map[i][j]);
                canNotDrawOne(i, j);
                visited[i][j]=false;
            }
        }
        System.out.println(result);


    }
    public static void dfs(int r, int c, int depth, int sum){
        if(depth==4){
            result=Math.max(result, sum);
            return;
        }
        for(int i=0;i<4;i++){
            int nx=r+dx[i];
            int ny=c+dy[i];
            if(isInRange(nx, ny)&&!visited[nx][ny]){
                visited[nx][ny]=true;
                dfs(nx, ny, depth+1, sum+map[nx][ny]);
                visited[nx][ny]=false;
            }
        }
    }
    public static void canNotDrawOne(int r, int c){
        int sum=0;
        if(isInRange(r,c+1)&&isInRange(r,c+2)&&isInRange(r+1,c+1)){
            sum+=map[r][c+1]+map[r][c+2]+map[r+1][c+1]+map[r][c];
            result=Math.max(sum, result);
        }
        sum=0;
        if(isInRange(r, c+1)&&isInRange(r, c+2)&&isInRange(r-1, c+1)){
            sum+=map[r][c+1]+map[r][c+2]+map[r][c]+map[r-1][c+1];
            result=Math.max(sum, result);
        }
        sum=0;
        if(isInRange(r+1, c)&&isInRange(r+2,c)&&isInRange(r+1, c+1)){
            sum+=map[r+1][c]+map[r+2][c]+map[r+1][c+1]+map[r][c];
            result=Math.max(sum, result);
        }
        sum=0;
        if(isInRange(r+1, c)&&isInRange(r+2,c)&&isInRange(r+1,c-1)){
            sum+=map[r][c]+map[r+1][c]+map[r+2][c]+map[r+1][c-1];
            result=Math.max(sum, result);
        }
    }
    public static boolean isInRange(int nx, int ny){
        if(nx<0||nx>=N||ny<0||ny>=M){
            return false;
        }
        return true;
    }
}
