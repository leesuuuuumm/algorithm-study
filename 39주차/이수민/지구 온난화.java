import java.io.*;
import java.util.*;

public class Main {

	static int R, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		}

		int minR = R;
		int maxR = 0;
		int minC = C;
		int maxC = 0;

		boolean[][] v = new boolean[R][C];

		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { 1, 0, -1, 0 };
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 'X') {

					int cnt = 0;
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];

						if (check(nr, nc) && map[nr][nc] == 'X')
							continue;
			
						cnt++;
					}

					if (cnt < 3) {
						v[r][c] = true;
						minR = Math.min(r, minR);
						minC = Math.min(c, minC);
						maxR = Math.max(r, maxR);
						maxC = Math.max(c, maxC);
					}

				}
			}
		}
		for (int r = minR; r <= maxR; r++) {
			for (int c = minC; c <= maxC; c++) {
				if (map[r][c] == 'X' && !v[r][c])
					map[r][c] = '.';
				System.out.print(map[r][c]);
			}
			System.out.println();
		}


	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
}
