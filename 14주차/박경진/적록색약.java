import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static char arr[][]; 
	static boolean visit[][];
	static int N;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); //그림의 크기
		arr = new char[N][N]; //색상 저장
		visit = new boolean[N][N]; //방문했는지 저장
		sc.nextLine();
		
		for(int i = 0; i < N; i++) {
			String s = sc.nextLine();
			for(int j = 0; j < N; j++) {
				arr[i][j] = s.charAt(j);
			}
		}
		
		int ans = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visit[i][j]) {
					dfso(i,j);
					ans++;
				}
			}
		}
		System.out.print(ans + " ");
		
		ans = 0;
		visit = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visit[i][j]) {
                    dfst(i,j);
					ans++;
				}
			}
		}
		System.out.println(ans);
		
	}
	
	
	public static void dfso(int a, int b) {
		int tx = a, ty = b;
		visit[tx][ty] = true;
		if(tx == N-1 && ty == N-1) return;
		
		
		for(int d = 0; d < 4; d++) {
			int nx = tx + dx[d];
			int ny = ty + dy[d];
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			if(visit[nx][ny]) continue;
			
			if(arr[tx][ty] == arr[nx][ny]) dfso(nx,ny);
			else if(d == 3) return;
			else continue;
		}
	}
	
	public static void dfst(int a, int b) {
		int tx = a, ty = b;
		visit[tx][ty] = true;
        
		for(int d = 0; d < 4; d++) {
			int nx = tx + dx[d];
			int ny = ty + dy[d];
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			if(visit[nx][ny]) continue;
            
			if(arr[tx][ty] == arr[nx][ny]) dfst(nx,ny);
			else if(arr[tx][ty] == 'R' && arr[nx][ny] == 'G') dfst(nx,ny);
			else if(arr[tx][ty] == 'G' && arr[nx][ny] == 'R') dfst(nx,ny);
			
			else continue;
		}
		return;
		
	}
}
