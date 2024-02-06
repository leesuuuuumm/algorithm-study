import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	static int house[][];
	static boolean visit[][];
	static int N = 0, sum = 0;
	
	public static void main(String[] args) {
		int danji = 0;
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //지도의 크기
		house = new int[N][N]; //정점끼리 연결돼있는지 체크
		visit = new boolean[N][N];
		sc.nextLine();
		
		ArrayList<Integer> count = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			String s = sc.nextLine();
			for(int j = 0; j < N; j++) {
				house[i][j] = Character.getNumericValue(s.charAt(j));
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(house[i][j] == 1 && !visit[i][j]) {
					danji++;
					sum = 1;
					count.add(dfs(i,j));
				}
				else visit[i][j] = true;
			}
		}
		
		Collections.sort(count);
		System.out.println(danji);
		for(int i = 0; i < count.size(); i++) System.out.println(count.get(i));
	}
	
	public static int dfs(int x, int y) {
		visit[x][y] = true;
		
		//상하좌우 탐색
		if(x > 0) {
			if(house[x-1][y] == 1 && !visit[x-1][y]) {
				sum++;
				dfs(x-1,y);
			}
		}
		if(y > 0) {
			if(house[x][y-1] == 1 && !visit[x][y-1]) {
				sum++;
				dfs(x,y-1);
			}
		}
		if(x < N-1) {
			if(house[x+1][y] == 1 && !visit[x+1][y]) {
				sum++;
				dfs(x+1,y);
			}
		}
		if(y < N-1) {
			if(house[x][y+1] == 1 && !visit[x][y+1]) {
				sum++;
				dfs(x,y+1);
			}
		}
		
		
		return sum;
	}
}
