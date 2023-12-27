package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2660_회장뽑기 {

	static final int INF = 100;
	static int N;
	static List<Integer>[] rel;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		input();
		solve();
	}

	private static void solve() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					if (arr[j][k] <= arr[j][i] + arr[i][k])
						continue;
					arr[j][k] = arr[j][i] + arr[i][k];
				}
			}
		}
		int[] scores = new int[N + 1];
		int minScore = Integer.MAX_VALUE;
		List<Integer> members = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;
				scores[i] = Math.max(scores[i], arr[i][j]);
			}
		}
		for (int i = 1; i <= N; i++) {
			if (minScore > scores[i]) {
				minScore = scores[i];
				members.clear();
				members.add(i);
			} else if (minScore == scores[i]) {
				members.add(i);
			}
		}

		StringBuilder result = new StringBuilder();
		result.append(minScore).append(" ").append(members.size()).append("\n");
		for (Integer member : members) {
			result.append(member).append(" ");
		}
		result.setLength(result.length() - 1);
		System.out.print(result);
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		rel = new List[N + 1];
		arr = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			rel[i] = new ArrayList<>();
			Arrays.fill(arr[i], INF);
		}
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());

			if (v1 == -1 && v2 == -1)
				break;

			rel[v1].add(v2);
			rel[v2].add(v1);
			arr[v1][v2] = 1;
			arr[v2][v1] = 1;
		}
	}
}
