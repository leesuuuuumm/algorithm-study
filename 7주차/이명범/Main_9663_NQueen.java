package boj;

public class Main_9663_NQueen {

	static int N;

	public static void main(String[] args) throws Exception {
		N = read();

		int result = 0;
		for (int i = 0; i < N; i++) {
			result += recur(0, i, new int[N]);
		}
		System.out.println(result / N);
	}

	private static int recur(int r, int c, int[] flag) {
		if (r == N)
			return 1;

		flag[r] = c;
		for (int i = 0; i < r; i++) {
			if (flag[r] == flag[i] || r - i == Math.abs(flag[r] - flag[i])) {
				flag[r] = 0;
				return 0;
			}
		}
		int result = 0;
		for (int i = 0; i < N; i++) {
			result += recur(r + 1, i, flag);
		}
		flag[r] = 0;
		return result;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
