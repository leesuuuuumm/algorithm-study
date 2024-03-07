package boj;

public class Main_2447_별찍기10 {

	static int N;
	static char[][] grid;

	public static void main(String[] args) throws Exception {
		input();
		recur(log3(N), 0, 0);
		output();
	}

	private static void output() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				char ch = grid[i][j];
				if (ch == '*')
					sb.append(ch);
				else
					sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	private static void recur(int cnt, int r, int c) {
		if (cnt == 0) {
			grid[r][c] = '*';
			return;
		}

		recur(cnt - 1, r, c);
		recur(cnt - 1, r, c + pow(cnt - 1));
		recur(cnt - 1, r, c + pow(cnt - 1) * 2);
		recur(cnt - 1, r + pow(cnt - 1), c);
		recur(cnt - 1, r + pow(cnt - 1), c + pow(cnt - 1) * 2);
		recur(cnt - 1, r + pow(cnt - 1) * 2, c);
		recur(cnt - 1, r + pow(cnt - 1) * 2, c + pow(cnt - 1));
		recur(cnt - 1, r + pow(cnt - 1) * 2, c + pow(cnt - 1) * 2);
	}

	private static int log3(int value) {
		return (int) (Math.log10(value) / Math.log10(3));
	}

	private static int pow(int cnt) {
		return (int) Math.pow(3, cnt);
	}

	private static void input() throws Exception {
		N = read();
		grid = new char[N][N];
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
