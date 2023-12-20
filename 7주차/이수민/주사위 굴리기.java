import java.io.*;
import java.util.*;

public class Main {

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;

		}

	}

	static int[][] map;
	static int N, M, x, y, K; 
	static int[] dice;

	static Queue<Point> que;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		que = new LinkedList<>();
		que.offer(new Point(x, y));
		dice = new int[7];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		while (K-- > 0) {
			moveDice(Integer.parseInt(st.nextToken()));
		}
		System.out.println(sb.toString());

	}

	static int[] dr = { 0, 0, -1, 1 }; 
	static int[] dc = { 1, -1, 0, 0 };

	private static void moveDice(int d) {
		Point cur = que.poll();

		int nr = dr[d - 1] + cur.r;
		int nc = dc[d - 1] + cur.c;

		if (!check(nr, nc)) {
			que.offer(new Point(cur.r, cur.c));
			return;
		}

		que.offer(new Point(nr, nc));
		simulDice(d);
		copyDice(nr, nc);

	}

	private static void simulDice(int d) {
		int tmp = 0;
		switch (d) {
		case 1:
			tmp = dice[2];
			dice[2] = dice[3];
			dice[3] = dice[4];
			dice[4] = dice[6];
			dice[6] = tmp;
			break;

		case 2:
			tmp = dice[6];
			dice[6] = dice[4];
			dice[4] = dice[3];
			dice[3] = dice[2];
			dice[2] = tmp;
			break;

		case 3:
			tmp = dice[6];
			dice[6] = dice[5];
			dice[5] = dice[3];
			dice[3] = dice[1];
			dice[1] = tmp;
			break;
		case 4:
			tmp = dice[1];
			dice[1] = dice[3];
			dice[3] = dice[5];
			dice[5] = dice[6];
			dice[6] = tmp;
			break;
		}
	}

	private static void copyDice(int nr, int nc) {
		if (map[nr][nc] != 0) { 
			dice[3] = map[nr][nc];
			map[nr][nc] = 0;
		} else {
			map[nr][nc] = dice[3];
		}
		sb.append(dice[6]).append("\n");
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}

}
