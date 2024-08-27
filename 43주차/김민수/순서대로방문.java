import java.io.*;
import java.util.*;

public class 순서대로방문 {
    static int[] dx={-1,0,1,0};
    static int[] dy={0,-1,0,1};
    static int result=0;
    static boolean[][] visited;
    static int[][] map;
    static int n, m;
    static int[][] pos; //0이면 x, 0보다 크면 i번째에 방문해야 하는 거

    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        visited=new boolean[n][n];
        map=new int[n][n];
        pos=new int[n][n];
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        int startX=1;
        int startY=1;
        for(int i=1;i<=m;i++){
            st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            if(i==1){
                startX=x-1;
                startY=y-1;
            }
            pos[x-1][y-1]=i;
        }
        visited[startX][startY]=true;
        dfs(startX, startY, 1);
        System.out.println(result);
    }

    public static void dfs(int x, int y, int step){
        if(step==m){
            result+=1;
          return;
        }
        for(int i=0;i<4;i++){
            int nx=x+dx[i];
            int ny=y+dy[i];
            if(isInRange(nx,ny)&&isValid(nx,ny)){
                if(pos[nx][ny]==0){
                    visited[nx][ny]=true;
                    dfs(nx,ny,step);
                    visited[nx][ny]=false;
                }else if(pos[nx][ny]==step+1){
                    visited[nx][ny]=true;
                    dfs(nx,ny,step+1);
                    visited[nx][ny]=false;
                }

            }
        }
    }

    public static boolean isInRange(int x, int y){
        if(x<0||y<0||x>=n||y>=n)
            return false;
        return true;
    }
    public static boolean isValid(int x, int y){
        if(visited[x][y]||map[x][y]!=0)
            return false;

        return true;
    }


    public static class Node{
        public int x, y, step;
        public Node(int x, int y, int step){
            this.x=x;
            this.y=y;
            this.step=step;
        }
    }
}
