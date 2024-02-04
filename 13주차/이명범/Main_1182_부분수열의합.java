package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1182_부분수열의합 {
	static int[] num;
	static int N, S;
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		input();
		dfs(0, 0);
		System.out.println(S == 0 ? answer - 1 : answer);
	}

	private static void dfs(int depth, int sum) {
		if (depth == N) {
			if (sum == S) answer++;
			return;
		}
		dfs(depth + 1, sum + num[depth]);
		dfs(depth + 1, sum);
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		num = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
	}
}
