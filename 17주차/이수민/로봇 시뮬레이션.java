import java.io.*;
import java.util.*;

public class Main {

	static class Point {
		int r;
		int c;
		int d;

		public Point(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	static int[][] map;
	static int A, B, N, M;
	static Point[] rb;
	static boolean f;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		rb = new Point[N + 1];
		map = new int[B][A];
		f = false;

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken()) - 1;
			int r = Integer.parseInt(st.nextToken()) - 1;
			String s = st.nextToken();
			map[r][c] = i;
			int d = 0;
			if (s.equals("N"))
				d = 1;
			else if (s.equals("W"))
				d = 2;
			else if (s.equals("S"))
				d = 3;

			rb[i] = new Point(r, c, d);
		}
		sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			String rt = st.nextToken();
			int loop = Integer.parseInt(st.nextToken());
			if (!f) {
				simulationRobot(num, rt, loop);
			}
		}
		if (sb.length() == 0) {
			sb.append("OK");
		}
		System.out.println(sb.toString());

		// A, B 맵크기
		// N개 로봇, M개 명령

	}

	static StringBuilder sb;

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	private static void simulationRobot(int num, String rt, int loop) {
		Point cur = rb[num];
		int nr = cur.r;
		int nc = cur.c;
		int nd = cur.d;
		e: for (int i = 0; i < loop; i++) {

			switch (rt) {
			case "R":
				nd--;
				if (nd < 0)
					nd = 3;
				continue e;
			case "L":
				nd++;
				if (nd > 3)
					nd = 0;
				continue e;
			}

			nr += dr[nd];
			nc += dc[nd];

			if (!check(nr, nc)) {
				sb.append("Robot ").append(num).append(" crashes into the wall");
				f = true;
				return;
			}
			if (map[nr][nc] != 0) {
				sb.append("Robot ").append(num).append(" crashes into robot ").append(map[nr][nc]);
				f = true;
				return;
			}
			map[nr - dr[nd]][nc - dc[nd]] = 0;
			map[nr][nc] = num;
		//	print();
		}
		rb[num] = new Point(nr, nc, nd);

	}

//	private static void print() {
//		for (int r = 0; r < B; r++) {
//			for (int c = 0; c < A; c++) {
//				System.out.print(map[r][c] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < B && nc >= 0 && nc < A;
	}

}
