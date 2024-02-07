import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int arr[][]; 
	static int visit[][];
	static int X, Y;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static Queue<Pair> q = new LinkedList<>();
	
	static class Pair{
		int x;
		int y;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Y = sc.nextInt(); //열, 가로
		X = sc.nextInt(); //행, 세로
		arr = new int[X][Y]; //토마토 정보
		visit = new int[X][Y]; //방문했는지 저장
		
		int one = 0;
		
		for(int i = 0; i < X; i++) {
			for(int j = 0; j < Y; j++) {
				arr[i][j] = sc.nextInt();
				if(arr[i][j] == -1) visit[i][j] = -1;
				if(arr[i][j] == 1) {
					q.add(new Pair(i,j));
					visit[i][j] = 1;
					one++;
				}
			}
		}
		
		if(one == X*Y) {
			System.out.println(0);
			return;
		}
		
		int ans = bfs();
		System.out.println(ans);
		
	}
	
	public static int bfs() {
		//Queue<Pair> q = new LinkedList<>();
		//q.add(new Pair(a,b));
		
		while(!q.isEmpty()) {
			//System.out.println("bfs while start");
			Pair p = q.poll();
			int tx = p.x; //현재 x좌표
			int ty = p.y; //현재 y좌표

			//System.out.println("tx is " + tx + " ty is " + ty);
			
			for(int d = 0; d < 4; d++) {
				int nx = tx + dx[d];
				int ny = ty + dy[d];
				
				if(nx < 0 || nx >= X) continue;
				if(ny < 0 || ny >= Y) continue;
				
				//System.out.println("nx is " + nx + " ny is " + ny);
				if(visit[nx][ny] == 0) {
					visit[nx][ny] = visit[tx][ty] + 1;
					q.add(new Pair(nx,ny));
				}
				else continue;
			}
		}
		
		int max = 0;
		for(int i = 0; i < X; i++) {
			for(int j = 0; j < Y; j++) {
				if(visit[i][j] == 0) return -1;
				if(visit[i][j] > max) max = visit[i][j];
			}
		}
		return max-1;
	}
}
