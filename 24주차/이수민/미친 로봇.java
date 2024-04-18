import java.util.*;
import java.io.*;

public class Main {

	static int[][] map;
	static int[] percent;
	static int N;
	static double ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		percent = new int[4];

		for (int i = 0; i < 4; i++) {
			percent[i] = Integer.parseInt(st.nextToken());
		}

		ans = 0;
		map = new int[30][30];

		map[14][14] = 1;
		dfs(0, 1, 14, 14);
		System.out.println(ans);

	}

	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	// 확률 계산하는 로직을 어떻게 처리해야할지 고민...
	// -> 25확률 <- 25확률 둘이 나올 확률은 곱하기 ...
	// 최종적으로 나올 수 있는 확률을 계속 누적 시켜주기???
	private static void dfs(int cnt, double per, int r, int c) {
		if (cnt == N) {
			ans += per;
			return;
		}

		for (int d = 0; d < 4; d++) {
			if (percent[d] == 0)
				continue;

			int nr = r + dr[d];
			int nc = c + dc[d];

			if (map[nr][nc] == 0) {
				map[nr][nc] = 1;
				dfs(cnt + 1, per * percent[d] * 0.01, nr, nc);
				map[nr][nc] = 0;
			}

		}

	}

}
