import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int arr[][]; 
	static boolean visit[][];
	static int dx[];
	static int dy[];
	static int l = 0;
	static int fx = 0, fy = 0;
	
	static class Chess{
		int x;
		int y;
		int cnt;
		Chess(int x, int y, int cnt){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); //테스트 케이스
		dx = new int[]{1,2,2,1,-1,-2,-2,-1};
		dy = new int[]{2,1,-1,-2,-2,-1,1,2};
		
		for(int i = 0; i < T; i++) {
			l = sc.nextInt(); //체스판의 크기
			visit = new boolean[l][l];
			
			int sx = sc.nextInt(); //시작점
			int sy = sc.nextInt(); //시작점
			fx = sc.nextInt(); //도착점
			fy = sc.nextInt(); //도착점
			
			bfs(sx,sy);
			
		}
	}
	
	public static void bfs(int x, int y) {
		Queue<Chess> q = new LinkedList<>();
		q.add(new Chess(x,y,0));
		visit[x][y] = true;
		
		
		
		while(!q.isEmpty()) {
			Chess c = q.poll();
			int tx = c.x;
			int ty = c.y;
			int cnt = c.cnt;
			
			if(tx == fx && ty == fy) {
				System.out.println(c.cnt);
				return;
			};
			
			for(int i = 0; i < 8; i++) {
				int a = tx + dx[i];
				int b = ty + dy[i];
				
				if(a >= 0 && a < l && b >= 0 && b < l && !visit[a][b]) {
					q.add(new Chess(a,b,cnt+1));
					visit[a][b] = true;
				}
			}
		}
		
	}
}
