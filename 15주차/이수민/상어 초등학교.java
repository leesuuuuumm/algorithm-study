import java.io.*;
import java.util.*;

public class Main {

	static class Student implements Comparable<Student> {
		int r;
		int c;
		int bfCnt;
		int empCnt;

		public Student(int r, int c, int bfCnt, int empCnt) {
			this.r = r;
			this.c = c;
			this.bfCnt = bfCnt;
			this.empCnt = empCnt;

		}

		public int compareTo(Student o) {
			if (this.bfCnt == o.bfCnt) {
				if (this.empCnt == o.empCnt) {
					if (this.r == o.r) {

						return Integer.compare(this.c, o.c);
					}
					return Integer.compare(this.r, o.r);
				}

				return Integer.compare(o.empCnt, this.empCnt);
			}
			return Integer.compare(o.bfCnt, this.bfCnt);

		}
	}

	static int[][] student;
	static int N;

	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;

		student = new int[N * N][5];
		for (int r = 0; r < student.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < student[0].length; c++) {
				student[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		map = new int[N][N];
		map[1][1] = student[0][0];
		pq = new PriorityQueue<>();
		changeSeat();
		System.out.println(preference());

	}

	private static int preference() {
		int sum = 0;
		for (int i = 0; i < student.length; i++) {
			a: for (int r = 0; r < map.length; r++) {
				for (int c = 0; c < map.length; c++) {
					int cnt = 0;
					if (map[r][c] == student[i][0]) {

						for (int d = 0; d < 4; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];

							if (!check(nr, nc))
								continue;

							for (int k = 1; k < student[i].length; k++) {
								if (map[nr][nc] == student[i][k]) {
									cnt++;
								}
							}
						}
						if (cnt == 0)
							continue;
						sum += Math.pow(10, cnt - 1);
						break a;
					}
				}
			}
		}
		return sum;

	}

	static PriorityQueue<Student> pq;

	private static void changeSeat() {
		for (int i = 1; i < N * N; i++) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == 0) {
						checkBf(i, r, c);
					}
				}
			}

			Student cur = pq.poll();
			map[cur.r][cur.c] = student[i][0];
			pq.clear();
		}

	}

	static int[] dr = { 0, 1, -1, 0 };
	static int[] dc = { -1, 0, 0, 1 };

	private static void checkBf(int i, int r, int c) {
		int cnt = 0;
		int emp = 0;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (!check(nr, nc))
				continue;

			for (int k = 1; k < student[i].length; k++) {
				if (map[nr][nc] == student[i][k]) {
					cnt++;
					break;
				}
			}

			if (map[nr][nc] == 0) {
				emp++;
			}
		}

		pq.offer(new Student(r, c, cnt, emp));
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
