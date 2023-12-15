package boj;

public class Main_1024_수열의합 {

	static int N, L;

	public static void main(String[] args) throws Exception {
		N = read();
		L = read();

		for (int i = L; i <= 100; i++) {
			int result = binarySearch(i);

			if (result == -1)
				continue;

			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < i; j++) {
				sb.append(result + j).append(" ");
			}
			System.out.println(sb);
			return;
		}
		System.out.println(-1);
	}

	private static int binarySearch(int length) {
		int l = 0;
		int r = N;

		while (l < r) {
			int m = (l + r) / 2;

			long sum = 0;
			for (int i = 0; i < length; i++) {
				sum += m + i;
			}

			if (sum < N) {
				l = m + 1;
			} else if (sum > N) {
				r = m;
			} else {
				return m;
			}
		}
		return -1;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
