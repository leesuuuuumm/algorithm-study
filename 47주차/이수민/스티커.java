import java.io.*;
import java.util.*;

public class Main {

	static int N;
    static int[] dr = { 1, 0, 1, 0, 0, 1 };
    static int[] dc = { 1, 2, 2, 1, 2, 2 };
    static int[][] map;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
        
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[2][N];
		
            int max = 0;

			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
            
			int[][] sum = new int[2][N];
            
			sum[0][0] = map[0][0];
			sum[1][0] = map[1][0];
            
			max = Math.max(Math.max(sum[0][0], sum[1][0]), max);

			for (int i = 0; i < N - 1; i++) {
				int d = 0;
				for (int j = 0; j < 2; j++) {
					for (int k = 0; k < 3; k++) {
						int nr = dr[d];
						int nc = i + dc[d];
                        
						d++;

						if (!check(nr, nc))
							continue;

						sum[nr][nc] = Math.max(sum[nr][nc], sum[j][i] + map[nr][nc]);
						max = Math.max(sum[nr][nc], max);
					}
				}
			}

			sb.append(max).append("\n");
		}
		System.out.println(sb);
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
