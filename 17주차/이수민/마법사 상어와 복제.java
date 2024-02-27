import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point> {
		int r;
		int c;
		int d;
		int cnt;
		int num;
		ArrayList<Point> list = new ArrayList<>();

		public Point(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public Point(int num, int cnt, ArrayList<Point> list) {
			this.num = num;
			this.cnt = cnt;
			this.list = list;
		}

		public int compareTo(Point o) {
			if (o.cnt == this.cnt) {
				return Integer.compare(this.num, o.num);
			}
			return Integer.compare(o.cnt, this.cnt);
		}
	}

	static int[][] smell;
	static int[][] fishCnt;
	static int M, S;
	static int sr, sc;
	static Queue<Point> fish;
	static Queue<Point> tmp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		fish = new LinkedList<>();
		smell = new int[4][4];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			fish.offer(new Point(r, c, d));
		}

		st = new StringTokenizer(br.readLine());
		sr = Integer.parseInt(st.nextToken()) - 1;
		sc = Integer.parseInt(st.nextToken()) - 1;

		sd = new int[64][3];
		selc = new int[3];
		count = 0;

		duplicateNPR(0);

		while (S-- > 0) {
			copyingMagicForFish();
			movingFishes();
			movingShark();
			removeSmellAndCopying();
		}
		System.out.println(fish.size());
	}

	private static void copyingMagicForFish() {
		// 상어가 모든 물고기 복제하기
		tmp = new LinkedList<>();
		for (Point i : fish) {
			tmp.offer(new Point(i.r, i.c, i.d));
		}
	}

	static int[] dr = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dc = { -1, -1, 0, 1, 1, 1, 0, -1 };

	private static void movingFishes() {
		// 물고기 움직이기
		// 조건: 냄새가 있는 칸, 격자의 범위를 벗어나는 칸, 상어 있는 칸 이동 못함

		fishCnt = new int[4][4]; // 물고기 개수 넣어주기

		int size = fish.size();

		e: for (int i = 0; i < size; i++) {
			Point cur = fish.poll();
			int d = cur.d;

			for (int j = 0; j < 8; j++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || smell[nr][nc] != 0 || sr == nr && sc == nc) {
					d--;
					if (d < 0)
						d = 7;

					continue;
				}

				fishCnt[nr][nc]++;
				fish.offer(new Point(nr, nc, d));
				continue e;
			}

			fishCnt[cur.r][cur.c]++;
			fish.offer(new Point(cur.r, cur.c, cur.d));
		}

	}

	private static void movingShark() {
		// 상어 움직이기
		// 조건: 물고기가 많은 칸으로 이동
		PriorityQueue<Point> pq = new PriorityQueue<>();

		e: for (int i = 0; i < 64; i++) {
			ArrayList<Point> t = new ArrayList<>();
			boolean[][] v = new boolean[4][4];

			int nr = sr;
			int nc = sc;
			int eats = 0;

			for (int d = 0; d < 3; d++) {
				nr += dr[sd[i][d]];
				nc += dc[sd[i][d]];

				if (!check(nr, nc)) {
					continue e;
				}

				if (!v[nr][nc]) {
					eats += fishCnt[nr][nc];
				}
				v[nr][nc] = true;
				t.add(new Point(nr, nc));

			}
			pq.offer(new Point(i, eats, t));
		}
		Point p = pq.poll();
		for (int i = 0; i < 3; i++) {
			Point cur = p.list.get(i);

			if (fishCnt[cur.r][cur.c] >= 1) {

				smell[cur.r][cur.c] = 3;
			}

			int size = fish.size();
			for (int j = 0; j < size; j++) {
				Point now = fish.poll();
				if (now.r == cur.r && now.c == cur.c) {
					continue;
				} else {
					fish.offer(new Point(now.r, now.c, now.d));
				}
			}
		}
		sr = p.list.get(2).r;
		sc = p.list.get(2).c;

	}

	private static void removeSmellAndCopying() {
		// 물고기 냄새 없애기 및 물고기 복제
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				if (smell[r][c] > 0) {
					smell[r][c]--;
				}
			}
		}
		for (Point i : tmp) {
			fish.offer(new Point(i.r, i.c, i.d));
		}
	}

	static int[][] sd;
	static int count;
	static int[] selc;

	// 상1 좌2 하3 우4
	private static void duplicateNPR(int cnt) {
		if (cnt == 3) {
			for (int i = 0; i < 3; i++) {
				if (selc[i] == 1) {
					sd[count][i] = 2;
				} else if (selc[i] == 2) {
					sd[count][i] = 0;
				} else if (selc[i] == 3) {
					sd[count][i] = 6;
				} else if (selc[i] == 4) {
					sd[count][i] = 4;
				}
			}
			count++;
			return;
		}

		for (int i = 1; i <= 4; i++) {
			selc[cnt] = i;

			duplicateNPR(cnt + 1);
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < 4 && nc >= 0 && nc < 4;
	}
}
