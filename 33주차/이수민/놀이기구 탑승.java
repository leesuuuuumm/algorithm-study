import java.util.*;
import java.io.*;
public class Main {
	static class Point implements Comparable<Point> {
		int r;
		int c;
		int like;
		int emp;

		public Point(int r, int c, int like, int emp) {
			this.r = r;
			this.c = c;
			this.like = like;
			this.emp = emp;
		}

		public int compareTo(Point o) {
			if (this.like == o.like) {
				return Integer.compare(o.emp, this.emp);
			}
			return Integer.compare(o.like, this.like);
		}
	}

	static int N;
	static int[][] map;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		arr = new int[N * N][5];

		for (int i = 0; i < N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		map[1][1] = arr[0][0];
		switchSeat();

		answer = 0;
		sumScore();
		System.out.println(answer);

	}

	static int answer;

	private static void sumScore() {
		for (int i = 0; i < N * N; i++) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == arr[i][0]) {
						int sc = 0;
						for (int d = 0; d < 4; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];

							if (!check(nr, nc))
								continue;

							for (int j = 1; j < arr[i].length; j++) {
								if (map[nr][nc] == arr[i][j]) {
									sc++;
									break;
								}
							}

						}
						if (sc != 0) {
							answer += (int) Math.pow(10, sc - 1);
						}
					}

				}
			}

		}

	}

	static int[] dr = { 0, 1, -1, 0 };
	static int[] dc = { 1, 0, 0, -1 };

	private static void switchSeat() {
		for (int i = 1; i < N * N; i++) {
			PriorityQueue<Point> pq = new PriorityQueue<>();

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					int likeCnt = 0;
					int empCnt = 0;
					if (map[r][c] != 0)
						continue;

					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];

						if (!check(nr, nc))
							continue;

						for (int j = 1; j < arr[i].length; j++) {
							if (arr[i][j] == map[nr][nc]) {
								likeCnt++;
								break;
							} else if (map[nr][nc] == 0) {
								empCnt++;
								break;
							}
						}
					}
					pq.offer(new Point(r, c, likeCnt, empCnt));
				}
			}
			Point cur = pq.poll();
			map[cur.r][cur.c] = arr[i][0];
		}

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
