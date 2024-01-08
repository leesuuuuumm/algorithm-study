import java.io.*;
import java.util.*;

public class Main {

	static class DirInfo {
		int time;
		String dir;

		public DirInfo(int time, String dir) {
			this.time = time;
			this.dir = dir;
		}
	}

	static class Snake {
		int r;
		int c;

		public Snake(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class Tail {
		int r;
		int c;

		public Tail(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, K, L; // 맵크기, 사과 위치, 방향 변환 개수
	static int[][] map;
	static List<DirInfo> list;
	static int cnt;
	static Queue<Tail> tail;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		list = new ArrayList<>();

		K = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r - 1][c - 1] = -1;
		}

		L = Integer.parseInt(br.readLine());

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new DirInfo(Integer.parseInt(st.nextToken()), st.nextToken()));
		}
		cnt = 0;
		map[0][0] = 1;
		que = new LinkedList<>();
		tail = new LinkedList<>();
		tail.offer(new Tail(0, 0));
		que.offer(new Snake(0, 0));

		startGame(0);
		System.out.println(cnt + 1);
	}

	static Queue<Snake> que;
	static int[] dr = { 0, 0, -1, 1 }; // 우 좌 상 하
	static int[] dc = { 1, -1, 0, 0 };

	private static void startGame(int d) {
		while (true) {
			Snake cur = que.poll();
			Tail curT;
			if (list.size() != 0 && cnt == list.get(0).time) {
				d = decideDirection(d);
			}
			int nr = cur.r + dr[d];
			int nc = cur.c + dc[d];

			if (!check(nr, nc) || (map[nr][nc] != 0 && map[nr][nc] != -1)) {

				break;
			}

			if (map[nr][nc] != -1) {
				curT = tail.poll();
				map[curT.r][curT.c] = 0;
			}
			map[nr][nc] = ++cnt;

			que.offer(new Snake(nr, nc));
			tail.offer(new Tail(nr, nc));
		}
	}

	private static int decideDirection(int d) {
		if (d == 0) {
			if (list.get(0).dir.equals("D")) {
				d = 3;
			} else {
				d = 2;
			}
		} else if (d == 1) {
			if (list.get(0).dir.equals("D")) {
				d = 2;
			} else {
				d = 3;
			}

		} else if (d == 2) {
			if (list.get(0).dir.equals("D")) {
				d = 0;
			} else {
				d = 1;
			}

		} else if (d == 3) {
			if (list.get(0).dir.equals("D")) {
				d = 1;
			} else {
				d = 0;
			}
		}
		list.remove(0);
		return d;

	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
