import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//DFS

public class 텀프로젝트 {
	static int[] map;
	static boolean[] visited;
	static boolean[] finished;
	static int teamCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			int n = Integer.parseInt(br.readLine());
			map = new int[n + 1];
			visited = new boolean[n + 1];
			finished = new boolean[n + 1];
			teamCount = 0;

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				int idx = Integer.parseInt(st.nextToken());
				map[j] = idx;
			}
			for (int k = 1; k <= n; k++) {
				if (!visited[k]) {
					dfs(k);
				}
			}
			System.out.println(n - teamCount);
		}
	}

	public static void dfs(int n) {
		visited[n] = true;
		int next = map[n];

		if (!visited[next]) {
			dfs(next);
		} else if (!finished[next]) { //사이클
			int temp = next;
			teamCount += 1; //사이클 시작점

			while (temp != n) {
				teamCount += 1;
				temp = map[temp];
			}
		}

		finished[n] = true;
	}
}
