import java.io.*;
import java.util.*;
public class Main {
   
	static int[][] map;
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		int sr = Integer.parseInt(st.nextToken());
		int sc = Integer.parseInt(st.nextToken());
		int sd = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		v = new boolean[N][M];

		simul(sr, sc, sd);
		System.out.println(cntStreet());

	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static boolean[][] v;

	private static void simul(int r, int c, int d) {

		a: while (true) {
			v[r][c] = true;

			for (int dd = 0; dd < 4; dd++) {
				d--;
				if (d < 0) {
					d = 3;
				}
				int nr = r + dr[d];
				int nc = c + dc[d];

				// System.out.println(nr + " " + nc + " " + d);
				if (!v[nr][nc] && map[nr][nc] == 0) {
					r = nr;
					c = nc;
					v[r][c] = true;
					continue a;
				}
			}
			int back = d;

			if (d == 0 || d == 1)
				back += 2;
			else
				back -= 2;

			if (map[r + dr[back]][c + dc[back]] == 1) {
				break;
			} else {
				r += dr[back];
				c += dc[back];
			}

		}

	}

	private static int cntStreet() {
		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
			//	System.out.print(v[r][c] + " ");
				if (v[r][c]) {
					cnt++;
				}
			}

		}
		return cnt;
	}

}
