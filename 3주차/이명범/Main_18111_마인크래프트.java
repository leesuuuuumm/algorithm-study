package boj;

public class Main_18111_마인크래프트 {

	static final int MAX_HEIGHT = 256;
	static int N, M, B;
	static int[][] map;

	public static void main(String[] args) throws Exception {
	    N = read();
		M = read();
		B = read();
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = read();
			}
		}
		int minTime = Integer.MAX_VALUE;
		int maxHeight = 0;
		for (int height = 0; height <= MAX_HEIGHT; height++) {
			int inventory = B;
			int needs = 0;
			int time = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					inventory += Math.max(0, map[r][c] - height);
					needs += Math.max(0, height - map[r][c]);
					time += Math.max(0, map[r][c] - height) * 2 + Math.max(0, height - map[r][c]);
				}
			}
			if (inventory < needs)
				continue;

			if (minTime >= time) {
				minTime = time;
				maxHeight = height;
			}
		}
		System.out.println(minTime + " " + maxHeight);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
