import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int idx;
		int cnt;

		public Point(int idx, int cnt) {
			this.idx = idx;
			this.cnt = cnt;
		}
	}

	static ArrayList<Point> list[];
	static int N;
	static int max = 0;
	static boolean visited[];
	static int maxIdx = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		list = new ArrayList[N + 1];

		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[parent].add(new Point(child, w));
			list[child].add(new Point(parent, w));
		}

		visited = new boolean[N + 1];
		visited[1] = true;
		dfs(1, 0);

		visited = new boolean[N + 1];
		visited[maxIdx] = true;
		dfs(maxIdx, 0);
		System.out.println(max);

	}

	public static void dfs(int idx, int cnt) {
		if (max < cnt) {
			max = cnt;
			maxIdx = idx;
		}

		for (Point i : list[idx]) {
			if (!visited[i.idx]) {
				visited[i.idx] = true;
				dfs(i.idx, cnt + i.cnt);
			}
		}
	}
}
