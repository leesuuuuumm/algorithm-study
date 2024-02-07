import java.io.*;
import java.util.*;

public class Main {
	static class Snake {
		int r;
		int c;

		public Snake(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, K, L;
	static int[][] map;
	static int time;
	static int sd;
	static String[] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		StringTokenizer st;

		map = new int[N][N];
		time = 0;

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = -1;
		}
		L = Integer.parseInt(br.readLine());
		dq = new ArrayDeque<>();
		dq.offerFirst(new Snake(0, 0));
		sd = 1;
		map[0][0] = 2;
		v = new String[10001];

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			v[Integer.parseInt(st.nextToken())] = st.nextToken();
		}
		gameStart();
		System.out.println(time);

	}

	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { -1, 1, 0, 0 };
	static Deque<Snake> dq;

	private static void gameStart() {
		while (true) {
			time++;
			Snake cur = dq.peekFirst();

			int nr = cur.r + dr[sd];
			int nc = cur.c + dc[sd];

			if (!check(nr, nc) || map[nr][nc] == 2) {
				return;
			}

			if (map[nr][nc] == 0) {
				map[dq.peekLast().r][dq.peekLast().c] = 0;
				dq.pollLast();
			}
			dq.offerFirst(new Snake(nr, nc));
			map[nr][nc] = 2;
			if (v[time] != null)
				changeDir(v[time]);

		}
	}

	private static void changeDir(String D) {
		if (D.equals("L")) {
			if (sd == 0)
				sd = 2;
			else if (sd == 1)
				sd = 3;
			else if (sd == 2)
				sd = 1;
			else if (sd == 3)
				sd = 0;
		} else if (D.equals("D")) {
			if (sd == 0)
				sd = 3;
			else if (sd == 1)
				sd = 2;
			else if (sd == 2)
				sd = 0;
			else if (sd == 3)
				sd = 1;
		}

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

}
