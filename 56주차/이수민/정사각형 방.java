import java.util.*;
import java.io.*;

public class Solution {
 
    static int map[][];
    static int N, T;
    static boolean[][] visit;
    static int dr[] = { 1, -1, 0, 0 };
    static int dc[] = { 0, 0, -1, 1 };
    static int max_cnt,k;
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            k=Integer.MAX_VALUE;
            max_cnt=0;
             
            for (int i = 0; i < N; i++) {
            	StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    visit = new boolean[N][N];
                    dfs(i, j,1,map[i][j]);
                     
                }
            }
            System.out.println("#"+t+" "+k+" "+max_cnt); 
        }
    }
 
    private static void dfs(int r, int c,int cnt,int a) {
 
        if(cnt>max_cnt) {
            max_cnt=cnt;
            k=a;
        }
        else if(cnt==max_cnt) {
            if(k>a) {
                k=a;
            }
        }
        visit[r][c] = true;
        for (int d = 0; d <4; d++) {
            int nr=r+dr[d];
            int nc=c+dc[d];
            if(!check(nr,nc)) {
                continue;
            }
            if(map[nr][nc]==map[r][c]+1&&!visit[nr][nc]) {
                dfs(nr,nc,cnt+1,a);
                 
            }
        }   
 
    }
 
    private static boolean check(int nr, int nc) {
    if(nr>=0&&nr<N&&nc>=0&&nc<N) {
        return true;
    }
    else
        return false;
    }
}
