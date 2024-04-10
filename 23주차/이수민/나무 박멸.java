import java.io.*;
import java.util.*;

public class Main {
	static class Tree {
		int r;
		int c;
		int cnt; // 나무 개수

		public Tree(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public Tree(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	static int[][] map;
	static int[][] jcmap;
	static int N, M, K, C; // 크기, 박멸이 진행되는 년수, 제초제의 확산 범위, 제초제가 남아있는 년수
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		jcmap = new int[N][N];
		v = new boolean[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		answer = 0;

		for (int m = 0; m < M; m++) {
			growTree();
			spreadTree();
			sprayJCJ();
			if (m >= 1) {
				decreaseJCJ();
			}
		}
		System.out.println(answer);

	}

	private static void decreaseJCJ() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (v[r][c] || jcmap[r][c] == 0)
					continue;
				jcmap[r][c] -= 1;
			}
		}

	}

	// 나무성장
	static int[] dr = { 0, 1, -1, 0 };
	static int[] dc = { 1, 0, 0, -1 };

	private static void growTree() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 나무가 있을 때
				if (map[r][c] > 0) {
					int cnt = 0;
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];

						if (!check(nr, nc))
							continue;

						if (map[nr][nc] > 0) {
							cnt++;
						}
					}
					map[r][c] += cnt;
				}
			}
		}
	}

	// 나무 번식
	private static void spreadTree() {
		// 번식할 경우는 4개 인접한 칸중 벽, 다른 나무, 제초제 모두 없는 칸에 번식 가능하다.
		// jcmap에는 제초제가 몇년 들어있는지 있는데 이게 0이여야한다.

		Queue<Tree> que = new LinkedList<>();
		ArrayList<Tree> list = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] > 0) {
					int cnt = 0;

					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];

						if (!check(nr, nc) || jcmap[nr][nc] != 0 || map[r][c] == -1)
							continue;

						if (map[nr][nc] == 0) {
							cnt++;
							list.add(new Tree(nr, nc));
						}
					}

					for (Tree t : list) {
						que.offer(new Tree(t.r, t.c, map[r][c] / cnt));
					}
					list.clear();

				}
			}
		}

		while (!que.isEmpty()) {
			Tree cur = que.poll();
			map[cur.r][cur.c] += cur.cnt;
		}
	}

	static int[] sr = { 1, -1, 1, -1 };
	static int[] sc = { -1, 1, 1, -1 };

	static boolean[][] v;

	// 제초제작업해서 나무 박멸
	private static void sprayJCJ() {
		int max = 0;
		int cr = -5;
		int cc = -5;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {

				// 경우 생각하기
				if (map[r][c] > 0) {

					int sum = map[r][c];
					for (int d = 0; d < 4; d++) {

						int nr = r; // 초기 값
						int nc = c;

						for (int i = 0; i < K; i++) {
							nr += sr[d];
							nc += sc[d];

							if (!check(nr, nc) || map[nr][nc] == -1 || map[nr][nc] == 0)
								break;

							sum += map[nr][nc];
						}
					}

					if (max < sum) {
						max = sum;
						cr = r;
						cc = c;

					}
				}
			}
		}
		if (cr == -5 && cc == -5)
			return;

		start(max, cr, cc);

	}

	private static void start(int max, int cr, int cc) {
		v = new boolean[N][N];

		map[cr][cc] = 0;
		jcmap[cr][cc] = C;
		v[cr][cc] = true;
		for (int d = 0; d < 4; d++) {
			int nr = cr;
			int nc = cc;
			for (int i = 0; i < K; i++) {
				nr += sr[d];
				nc += sc[d];

				if (!check(nr, nc))
					break;

				if (map[nr][nc] == 0 || map[nr][nc] == -1) {
					jcmap[nr][nc] = C;
					v[nr][nc] = true;
					break;
				}

				map[nr][nc] = 0;
				jcmap[nr][nc] = C;
				v[nr][nc] = true;
			}

		}
		answer += max;

	}


	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
