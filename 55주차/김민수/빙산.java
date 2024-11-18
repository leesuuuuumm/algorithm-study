import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 빙산 {
	static int N, M;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static boolean[][] visited;
	static int count;
	static int depth;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		count = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0)
					count += 1;
			}
		}

		int answer = 0;
		while (true) {
			depth = 0;
			boolean canNot = true;
			int num = 0;
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (!visited[i][j] && map[i][j] > 0) {
						simulation(i, j);
						num += 1;
						canNot = false;
					}
				}
			}
			if (canNot) {
				System.out.println(0);
				return;
			}
			if (num > 1) {
				System.out.println(answer);
				return;
			}
			answer += 1;
		}

	}

	public static void simulation(int x, int y) {
		visited[x][y] = true;
		int zero = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (isInRange(nx, ny) && !visited[nx][ny]) {
				if (map[nx][ny] == 0) {
					zero += 1;
				} else {
					simulation(nx, ny);
				}
			}
		}
		map[x][y] -= zero;
		map[x][y] = Math.max(map[x][y], 0);
	}

	public static boolean isInRange(int nx, int ny) {
		return (nx >= 0 && ny >= 0 && nx < N && ny < M);
	}
}
