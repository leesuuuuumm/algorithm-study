import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	static boolean flag;
	static int answer, N, min, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][3];

		for (int i = 1; i < map.length-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		K = Integer.parseInt(br.readLine());
		answer = 0;
		min = Integer.MAX_VALUE;
		flag = false;

		dfs(1);
		System.out.println(min);
	}

	private static void dfs(int cnt) {
		if (cnt > N) {
			return;
		}
		if (cnt == N) {
			min = Math.min(answer, min);
			return;
		}

		for (int i = 1; i <= 3; i++) { // 1칸 2칸 3칸
			if (i == 3 && !flag) {
				answer += K;
				flag = true;
				dfs(cnt + i);
				flag = false;
				answer -= K;
			} else if (i < 3) {
				answer += map[cnt][i];
				dfs(cnt + i);
				answer -= map[cnt][i];
			}
		}

	}

}
