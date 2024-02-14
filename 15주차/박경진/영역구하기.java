import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	static int arr[][]; 
	static boolean visit[][];
	static int M, N, K, cnt;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt(); //행, 세로
		N = sc.nextInt(); //열, 가로
		K = sc.nextInt(); //사각형의 개수
		
		arr = new int[M][N];
		visit = new boolean[M][N];
		
		ArrayList<Integer> answer = new ArrayList<>();
		int sum = 0;
		
		for(int i = 0; i < K; i++) {
			int sy = sc.nextInt();
			int sx = sc.nextInt();
			int fy = sc.nextInt();
			int fx = sc.nextInt();
			
			for(int j = 0; j < M; j++) {
				for(int z = 0; z < N; z++) {
					if(sx <= j && fx > j && sy <= z && fy > z) {
						arr[j][z]++;
					}
				}
			}
		}
		
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				cnt = 1;
				if(arr[i][j] == 0 && !visit[i][j]) {
					sum++;
					dfs(i,j);
					answer.add(cnt);
				}
			}
		}
		
		Collections.sort(answer);
		System.out.println(sum);
		while(!answer.isEmpty()) System.out.print(answer.remove(0) + " ");
		
	}
	
	public static void dfs(int x, int y) {
		visit[x][y] = true;
		
		if(x > 0) {
			if(arr[x-1][y] == 0 && !visit[x-1][y]) {
				cnt++;
				dfs(x-1,y);
			}
		}
		if(x < M-1) {
			if(arr[x+1][y] == 0 && !visit[x+1][y]) {
				cnt++;
				dfs(x+1,y);
			}
		}
		if(y > 0) {
			if(arr[x][y-1] == 0 && !visit[x][y-1]) {
				cnt++;
				dfs(x,y-1);
			}
		}
		if(y < N-1) {
			if(arr[x][y+1] == 0 && !visit[x][y+1]) {
				cnt++;
				dfs(x,y+1);
			}
		}
	}
}
