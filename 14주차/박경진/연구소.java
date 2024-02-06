import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int arr[][]; 
	//static boolean visit[][];
	static int X, Y;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int max = 0;
	
	static class Pair{
		int x;
		int y;
		Pair(int a, int b){
			this.x = a;
			this.y = b;
		}
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		X = sc.nextInt(); //행, 세로
		Y = sc.nextInt(); //열, 가로
		arr = new int[X][Y]; //연구소
		
		for(int i = 0; i < X; i++) {
			for(int j = 0; j < Y; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		dfs(0);
		System.out.println(max);
	}
	
	
	public static void dfs(int w) { //벽세우기
		if(w == 3) {
			bfs(); //벽 세 개가 다 세워졌으면 바이러스 전파
			return;
		}
		
		for(int i = 0; i < X; i++)
		{
			for(int j = 0; j < Y; j++) {
				if(arr[i][j] == 0) { //벽이 없으면
					arr[i][j] = 1; //벽을 세우고
					dfs(w+1); //개수를 증가시킴
					arr[i][j] = 0; //다음번을 위해 벽 철거
				}
			}
		}
	}
	
	public static void bfs() { //바이러스 ㅍ트리기
		Queue<Pair> q = new LinkedList<>();
		
		for(int i = 0; i < X; i++) {
			for(int j = 0; j < Y; j++) { 
				if(arr[i][j] == 2) { //바이러스 근원지를 큐에 저장
					q.add(new Pair(i,j));
				}
			}
		}
		
		int[][] map = new int[X][Y]; //바이러스가 퍼질 맵 준비
		for(int i = 0; i < X; i++)
			map[i] = arr[i].clone();
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			int tx = p.x;
			int ty = p.y;
			

			for(int d = 0; d < 4; d++) { //근원지의 상하좌우를 탐색하며 전파 시작
				int nx = tx + dx[d];
				int ny = ty + dy[d];
				if(nx < 0 || nx >= X) continue;
				if(ny < 0 || ny >= Y) continue;
				
				if(map[nx][ny] == 0) {
					q.add(new Pair(nx,ny));
					map[nx][ny] = 2;
				}
			}
		}
		
		safeZone(map);
	}
	
	public static void safeZone(int[][] map) {
		int safe = 0;
		
		for(int i = 0; i < X; i++) {
			for(int j = 0; j < Y; j++) {
				if(map[i][j] == 0) safe++;
			}
		}
		
		if(safe > max) max = safe;
	}
}
