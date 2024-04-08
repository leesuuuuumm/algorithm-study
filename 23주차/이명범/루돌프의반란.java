import java.util.Arrays;

public class Main {

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, M, P, C, D;
	static Point currentPointOfRudolf;
	static Point[] currentPointsOfSanta;
	static int[][] grid;
	static int[] stun, scores;


	/*
	1. 루돌프가 가장 가까운 산타에게 돌진
		1-1. 가장 가까운 산타를 찾는다.
		1-2. 어느 방향으로 이동해야 할 지 찾는다.
		1-3. 해당 방향으로 한 칸 이동한다.
		1-4. 만약 해당 위치에 산타가 있다면 3을 실행한다.
	2. 산타가 루돌프에게 이동
		2-1. 현재 루돌프의 위치를 찾는다.
		2-2. 어느 방향으로 이동해야 할 지 찾는다.
		2-3. 해당 방향으로 한 칸 이동한다.
		2-4. 만약 해당 위치에 루돌프가 있다면 3을 실행한다.
	*/
	public static void main(String[] args) throws Exception {
		input();
		for (int i = 0; i < M; i++) {
			Point currentPointOfClosestSanta = getCurrentPointOfClosestSanta();
			if (currentPointOfClosestSanta.r == 10000 && currentPointOfClosestSanta.c == 10000)
				break;
			Point directionOfRudolf = getDirectionOfRudolf(currentPointOfRudolf, currentPointOfClosestSanta);

			int nr = currentPointOfRudolf.r + directionOfRudolf.r;
			int nc = currentPointOfRudolf.c + directionOfRudolf.c;

			if (grid[nr][nc] != 0)
				collision(grid[nr][nc], nr, nc, directionOfRudolf.r, directionOfRudolf.c, C, i);

			currentPointOfRudolf.r = nr;
			currentPointOfRudolf.c = nc;

			for (int j = 1; j <= P; j++) {
				if (currentPointsOfSanta[j] == null)
					continue;
				if (stun[j] >= i)
					continue;

				Point directionOfSanta = getDirectionOfSanta(currentPointsOfSanta[j], currentPointOfRudolf);

				int nnr = currentPointsOfSanta[j].r + directionOfSanta.r;
				int nnc = currentPointsOfSanta[j].c + directionOfSanta.c;

				grid[currentPointsOfSanta[j].r][currentPointsOfSanta[j].c] = 0;
				grid[nnr][nnc] = j;
				currentPointsOfSanta[j].r = nnr;
				currentPointsOfSanta[j].c = nnc;

				if (currentPointOfRudolf.r == nnr && currentPointOfRudolf.c == nnc)
					collision(j, nnr, nnc, -directionOfSanta.r, -directionOfSanta.c, D, i);
			}

			for (int j = 1; j <= P; j++) {
				if (currentPointsOfSanta[j] == null)
					continue;

				scores[j]++;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= P; i++) {
			sb.append(scores[i]).append(" ");
		}
		System.out.println(sb);
	}

	private static Point getDirectionOfSanta(Point current, Point target) {
		int minDistance = getDistance(current, target);
		Point result = new Point(0, 0);

		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};

		for (int i = 0; i < 4; i++) {
			int nr = current.r + dr[i];
			int nc = current.c + dc[i];

			if (isArrayOutOfBounds(nr, nc))
				continue;
			if (grid[nr][nc] != 0)
				continue;
			Point next = new Point(nr, nc);
			if (minDistance <= getDistance(next, target))
				continue;

			minDistance = getDistance(next, target);
			result = new Point(dr[i], dc[i]);
		}
		return result;
	}

	private static void collision(int no, int r, int c, int dr, int dc, int power, int turn) {
		int nr = r + power * dr;
		int nc = c + power * dc;

		grid[r][c] = 0;
		scores[no] += power;
		if (isArrayOutOfBounds(nr, nc)) {
			stun[no] = M;
			currentPointsOfSanta[no] = null;
			return;
		}
		if (grid[nr][nc] != 0)
			chain(grid[nr][nc], nr, nc, dr, dc);

		currentPointsOfSanta[no].r = nr;
		currentPointsOfSanta[no].c = nc;
		grid[nr][nc] = no;
		stun[no] = turn + 1;
	}

	private static void chain(int no, int r, int c, int dr, int dc) {
		int nr = r + dr;
		int nc = c + dc;

		grid[r][c] = 0;
		if (isArrayOutOfBounds(nr, nc)) {
			stun[no] = M;
			currentPointsOfSanta[no] = null;
			return;
		}
		if (grid[nr][nc] != 0)
			chain(grid[nr][nc], nr, nc, dr, dc);

		currentPointsOfSanta[no].r = nr;
		currentPointsOfSanta[no].c = nc;
		grid[nr][nc] = no;
	}

	private static boolean isArrayOutOfBounds(int r, int c) {
		return r <= 0 || r > N || c <= 0 || c > N;
	}

	private static Point getDirectionOfRudolf(Point current, Point target) {
		int nr = 0, nc = 0;

		if (current.r < target.r)
			nr = 1;
		else if (current.r > target.r)
			nr = -1;

		if (current.c < target.c)
			nc = 1;
		else if (current.c > target.c)
			nc = -1;

		return new Point(nr, nc);
	}

	private static Point getCurrentPointOfClosestSanta() {
		Point result = new Point(10000, 10000);
		for (int i = 1; i <= P; i++) {
			if (currentPointsOfSanta[i] == null)
				continue;
			if (isCloser(currentPointsOfSanta[i], result))
				result = currentPointsOfSanta[i];
		}
		return result;
	}

	private static boolean isCloser(Point current, Point compared) {
		int currentDistance = getDistance(currentPointOfRudolf, current);
		int comparedDistance = getDistance(currentPointOfRudolf, compared);

		if (currentDistance < comparedDistance)
			return true;
		if (currentDistance == comparedDistance && current.r > compared.r)
			return true;
		if (currentDistance == comparedDistance && current.r == compared.r && current.c > compared.c)
			return true;

		return false;
	}

	private static int getDistance(Point p1, Point p2) {
		return (p1.r - p2.r) * (p1.r - p2.r) + (p1.c - p2.c) * (p1.c - p2.c);
	}

	private static void input() throws Exception {
		N = read();
		M = read();
		P = read();
		C = read();
		D = read();
		currentPointOfRudolf = new Point(read(), read());
		currentPointsOfSanta = new Point[P + 1];
		grid = new int[N + 1][N + 1];
		stun = new int[P + 1];
		Arrays.fill(stun, -1);
		scores = new int[P + 1];
		for (int i = 0; i < P; i++) {
			int no = read();
			int r = read();
			int c = read();
			currentPointsOfSanta[no] = new Point(r, c);
			grid[r][c] = no;
		}
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
