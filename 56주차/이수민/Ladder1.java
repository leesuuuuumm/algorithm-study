package algorithm.study;

import java.util.*;
import java.io.*;

public class Solution {
 
   
    static int N=100,M;
    static int[][] map;
    
    static int[] dy= {-1,0,0}; 
    static int[] dx= {0,-1,1};
 
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        for (int t = 1; t <=10; t++) {
            M= Integer.parseInt(br.readLine());
            map=new int[N][N];
            
            int sy=0;
            int sx=0;
            for (int r = 0; r < N; r++) {
            	StringTokenizer st =new StringTokenizer(br.readLine());
                for (int c = 0; c <N; c++) {
                    map[r][c]= Integer.parseInt(st.nextToken());
                    if(map[r][c]==2) {
                        sy=r;
                        sx=c;
                    }
                }
            }
            int d=0;
            while(sy!=0) {
                if(d==0) {  
                    if(check(sy-1, sx) && map[sy-1][sx]==1
                    && (!check(sy, sx-1) || map[sy][sx-1]==0) 
                    && (!check(sy, sx+1) || map[sy][sx+1]==0)) {
                        d=0;
                    }else if(check(sy, sx-1) && map[sy][sx-1]==1) {
                        d=1;
                    }else if(check(sy, sx+1) && map[sy][sx+1]==1) {
                        d=2;
                    }
                }else if(d==1) {
                    if(check(sy, sx-1) && map[sy][sx-1]==1) {
                        d=1;
                    }else if(check(sy-1, sx) && map[sy-1][sx]==1) {
                        d=0;
                    } 
                }else if(d==2) {
                    if(check(sy, sx+1) && map[sy][sx+1]==1) {
                        d=2;
                    }else if(check(sy-1, sx) && map[sy-1][sx]==1) {
                        d=0;
                    } 
                }
                sy+=dy[d];
                sx+=dx[d];
            }
            System.out.println("#"+t+" "+sx);
             
        }
    }
 
    private static boolean check(int ny, int nx) {
        return ny>=0 && ny<N && nx>=0 && nx<N;
    }
}
