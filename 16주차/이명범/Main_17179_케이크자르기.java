package boj;

public class Main_17179_케이크자르기 {

	static int N, M, L;
	static int[] length;

	public static void main(String[] args) throws Exception {
		N = read();
		M = read();
		L = read();
		length = new int[M + 1];
		for (int i = 0; i < M; i++) {
			length[i] = read();
		}
		length[M] = L;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(binarySearch(read())).append("\n");
		}
		System.out.print(sb);
	}
	private static int binarySearch(int count) {
		int l = 0;
		int r = L;

		int answer = 0;
		while (l <= r) {
			int m = (l + r) / 2;

			if (check(m, count)) {
				l = m + 1;
				answer = Math.max(m, answer);
			} else {
				r = m - 1;
			}
		}
		return answer;
	}

	private static boolean check(int m, int count) {
		int prev = 0;
		int result = 0;
		for (int i = 0; i <= M; i++) {
			if (length[i] - prev < m)
				continue;
			result++;
			prev = length[i];
		}
		return result > count;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
