import java.io.*;
import java.util.*;

public class Main {

	static int[][] travel;
	static int N, M;
	static int[] route;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		travel = new int[N + 1][N + 1];
		route = new int[M];
		travel[0][0] = 0;
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			travel[i][0] = 0;
			for (int j = 1; j <= N; j++) {
				travel[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			route[i] = Integer.parseInt(st.nextToken());
		}

		check = new boolean[N + 1];

		check[route[0]] = true;
		dfs(route[0]); 
		for (int i = 0; i < M; i++) {
			if (!check[route[i]]) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");

	}

	static boolean[] check;

	private static void dfs(int now) {
		for (int i = 1; i <= N; i++) {
			if (!check[i] && travel[now][i] == 1) {
				check[i] = true;
				dfs(i);
			}
		}

	}

}
