public class Main {

	static class Knight {
		int no;
		int r;
		int c;
		int h;
		int w;
		int k;
		int d;

		public Knight(int no, int r, int c, int h, int w, int k) {
			this.no = no;
			this.r = r;
			this.c = c;
			this.h = h;
			this.w = w;
			this.k = k;
			this.d = 0;
		}

		public boolean isDead() {
			return this.k <= 0;
		}

		public void process(int dir) {
			if (this.isDead())
				return;
			if (isBlocked(dir, 1 << this.no))
				return;

			int processedKnights = getProcessedKnights(dir, 1 << this.no);

			for (int i = 1; i <= N; i++) {
				if ((processedKnights & 1 << i) == 0)
					continue;

				knights[i].move(dir);
				
				if (this.no == i)
					continue;
				
				knights[i].suffer();
			}
		}

		private void suffer() {
			for (int i = r; i < r + h; i++) {
				for (int j = c; j < c + w; j++) {
					if (board[i][j] != 1)
						continue;
					d++;
					k--;
				}
			}
		}

		private void move(int dir) {
			this.r += dr[dir];
			this.c += dc[dir];
		}

		private int getProcessedKnights(int dir, int flag) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];

			for (int i = 1; i <= N; i++) {
				if (knights[i].isDead())
					continue;
				if ((flag & 1 << i) != 0)
					continue;
				if (!isOverlapped(nr, nc, knights[i]))
					continue;

				flag |= knights[i].getProcessedKnights(dir, flag | 1 << i);
			}

			return flag;
		}

		private boolean isBlocked(int dir, int flag) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];

			if (isArrayOutOfBounds(nr, nc))
				return true;
			if (isArrayOutOfBounds(nr + h - 1, nc + w - 1))
				return true;

			for (int j = nr; j < nr + h; j++) {
				for (int l = nc; l < nc + w; l++) {
					if (board[j][l] == 2)
						return true;
				}
			}

			for (int i = 1; i <= N; i++) {
				if (knights[i].isDead())
					continue;
				if ((flag & 1 << i) != 0)
					continue;
				if (!isOverlapped(nr, nc, knights[i]))
					continue;

				if (knights[i].isBlocked(dir, flag | 1 << i))
					return true;
			}
			return false;
		}

		private boolean isOverlapped(int r, int c, Knight knight) {
			if (r > knight.r + knight.h - 1)
				return false;
			if (c > knight.c + knight.w - 1)
				return false;
			if (r + h - 1 < knight.r)
				return false;
			if (c + w - 1 < knight.c)
				return false;

			return true;
		}
	}
	static int L, N, Q, result;
	static int[][] board;
	static Knight[] knights;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		input();
		for (int q = 0; q < Q; q++) {
			int i = read();
			int d = read();

			knights[i].process(d);
		}
		for (int i = 1; i <= N; i++) {
			if (knights[i].isDead())
				continue;
			result += knights[i].d;
		}
		System.out.println(result);
	}

	private static boolean isArrayOutOfBounds(int r, int c) {
		return r <= 0 || r > L || c <= 0 || c > L;
	}

	private static void input() throws Exception {
		L = read();
		N = read();
		Q = read();
		board = new int[L + 1][L + 1];
		knights = new Knight[N + 1];
		for (int i = 1; i <= L; i++) {
			for (int j = 1; j <= L; j++) {
				board[i][j] = read();
			}
		}
		for (int no = 1; no <= N; no++) {
			int r = read();
			int c = read();
			int h = read();
			int w = read();
			int k = read();
			knights[no] = new Knight(no, r, c, h, w, k);
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
