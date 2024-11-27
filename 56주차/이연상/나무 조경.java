// [SOFT] 나무 조경

import java.io.*;
import java.util.*;

public class Main {

    static int ans = 0, n = 0;

    static int dx[] = {0,1};
    static int dy[] = {1,0};
    static int maps[][];
    static boolean visited[][];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        maps = new int[n][n];
        visited = new boolean[n][n];
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                maps[i][j] = sc.nextInt();
            }
        }
        int maxDepth = 4;
        if(n==2) maxDepth =2;
        
        dfs(0,0,maxDepth);
        
        System.out.println(ans);
        
    }

    static void dfs(int cnt, int sum,int maxDepth)
    {
        if(cnt == maxDepth)
        {
            ans = Math.max(ans,sum);
            return;
        }

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(visited[i][j]) continue;
                for(int d =0;d<2;d++){
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if( 0<= nx && nx <n && 0<= ny && ny < n && !visited[nx][ny])
                    {
                        visited[i][j] = true;
                        visited[nx][ny] = true;
                        dfs(cnt+1,sum + maps[i][j] + maps[nx][ny],maxDepth);
                        visited[i][j] = false;
                        visited[nx][ny] = false;
                        
                    }
                }
            }
        }
    }
}