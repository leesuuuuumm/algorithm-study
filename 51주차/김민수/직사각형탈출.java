import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 직사각형탈출 {
	static int N, M;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static boolean[][] canNotGo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		canNotGo = new boolean[N + 1][M + 1];
		st = new StringTokenizer(br.readLine());
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int sR = Integer.parseInt(st.nextToken());
		int sC = Integer.parseInt(st.nextToken());
		int eR = Integer.parseInt(st.nextToken());
		int eC = Integer.parseInt(st.nextToken());
		int startR = 0;
		int startC=0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j] == 1) {
					startR = Math.max(1, i - (h - 1));
					startC = Math.max(1, j - (w - 1));
					for (int k = startR; k <= i; k++) {
						for (int l = startC; l <= j; l++) {
							canNotGo[k][l] = true;
						}
					}
				}
			}
		}
		System.out.println(bfs(h, w, sR, sC, eR, eC));
	}

	public static int bfs(int h, int w, int startR, int startC, int endR, int endC) {
		Queue<Node> que = new ArrayDeque<>();
		que.add(new Node(startR, startC, 0));
		boolean[][] visited = new boolean[N + 1][M + 1];
		visited[startR][startC] = true;
		while (!que.isEmpty()) {
			Node n = que.poll();
			for (int i = 0; i < 4; i++) {
				int nx = n.r + dx[i];
				int ny = n.c + dy[i];
				boolean flag = false;

				if (isInRange(nx,ny)&&!visited[nx][ny] && !canNotGo[nx][ny]) {
					if (isInRange(nx+h-1,ny+w-1)&&!flag) {
						if (nx == endR && ny == endC) {
							return n.count + 1;
						} else {
							visited[nx][ny] = true;
							que.add(new Node(nx, ny, n.count + 1));
						}
					}
				}

			}
		}
		return -1;
	}

	static class Node {
		public int r, c;
		public int count;

		Node(int r, int c, int count) {
			this.r = r;
			this.c = c;
			this.count = count;
		}
	}

	static boolean isInRange(int dx, int dy) {
		return (dx >= 1 && dy >= 1 && dx <= N && dy <= M);
	}
}
