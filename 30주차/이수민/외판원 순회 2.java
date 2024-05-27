import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] map;
	static boolean[] v;
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		min = Integer.MAX_VALUE;
		v = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			v[i] = true;
			dfs(i, i, 0, 0);

		}
		System.out.println(min);

	}

	private static void dfs(int root, int now, int cost, int cnt) {
		if (cnt == N - 1) {
			if (map[now][root] != 0) {
				cost += map[now][root];
				min = Math.min(cost, min);
			}
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (!v[i] && map[now][i] != 0) {
				v[i] = true;
				dfs(root, i, cost + map[now][i], cnt + 1);
				v[i] = false;
			}

		}

	}

}
