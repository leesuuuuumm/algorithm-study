import java.io.*;
import java.util.*;

public class Main {

	static int[][] map = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 0, -1, -1 } };
	static int[] dr = { 0, 1, -1, 0 };
	static int[] dc = { 1, 0, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[][] dp = new int[1001][10];
		Arrays.fill(dp[1], 1);
		StringBuilder sb = new StringBuilder();
		
		
		for(int i=2;i<=1000;i++) {
			for(int r=0;r<4;r++) {
				for(int c=0;c<3;c++) {
					if(map[r][c] == -1) continue;
					for(int d=0;d<4;d++) {
						int nr = r+dr[d];
						int nc = c+dc[d];
						if(!check(nr,nc)|| map[nr][nc] == -1) continue;

						dp[i][map[r][c]]+= (dp[i-1][map[nr][nc]]);
						dp[i][map[r][c]]%=1234567;
					}
				}
			}
		}
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int ans = 0;
			for(int i=0;i<=9;i++) {
				ans+= dp[N][i];
				ans %=1234567;
			}
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < 4 && nc >= 0 && nc < 3;
	}
}
