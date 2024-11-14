package solve;

import java.util.*;
import java.io.*;

public class 게임 {
	static int N, M;
	static int[][] map;
	
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	static boolean[][] visited;
	static int answer;
	static boolean flag;
	static int[][]dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			char[] str = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				int num = 0;
				if (str[j] != 'H') {
					num = str[j] - '0';
				}
				map[i][j] = num;
			}
		}
		
		visited=new boolean[N][M];
		visited[0][0]=true;
		
		dp=new int[N][M];
		flag=false;
		
		dp[0][0]=1;
		dfs(0,0,1);
		
		if(flag)
			System.out.println(-1);
		else
			System.out.println(answer);

	}

	public static void dfs(int x, int y, int depth){
		answer=Math.max(answer, depth);
		if(flag)
			return;
		
		int c=map[x][y];
		for(int i=0;i<4;i++) {
			int nx=x+c*dx[i];
			int ny=y+c*dy[i];
			if(isInRange(nx,ny)&&map[nx][ny]>0&&dp[nx][ny]<depth+1) {
				if(visited[nx][ny]) {
					flag=true;
				}else {
					visited[nx][ny]=true;
					dp[nx][ny]=depth+1;
					dfs(nx,ny,depth+1);
					visited[nx][ny]=false;
				}
				
			}
		}
	}
	
	public static class Step{
		int x, y, count;
		
		public Step(int x, int y, int count) {
			this.x=x;
			this.y=y;
			this.count=count;
		}
	}

	public static boolean isInRange(int nx, int ny) {
		return (nx >= 0 && ny >= 0 && nx < N && ny < M);
	}

}
