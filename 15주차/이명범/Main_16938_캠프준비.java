package boj;

public class Main_16938_캠프준비 {

	static int N, L, R, X, result;
	static int[] values;

	public static void main(String[] args) throws Exception {
		input();
		calculate(0, 0, Integer.MAX_VALUE, 0);
		calculate(0, values[0], values[0], values[0]);
		System.out.println(result);
	}

	private static void input() throws Exception {
		N = read();
		L = read();
		R = read();
		X = read();
		values = new int[N];
		for (int i = 0; i < N; i++) {
			values[i] = read();
		}
	}

	private static void calculate(int cnt, int sum, int min, int max) {
		if (cnt == N - 1) {
			if (cnt >= 1 && sum >= L && sum <= R && max - min >= X)
				result++;
			return;
		}
		int next = values[cnt + 1];
		calculate(cnt + 1, sum + next, Math.min(min, next), Math.max(max, next));
		calculate(cnt + 1, sum, min, max);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
