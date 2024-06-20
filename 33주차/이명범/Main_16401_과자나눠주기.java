package boj;

public class Main_16401_과자나눠주기 {

	static int M, N;
	static int[] L;

	public static void main(String[] args) throws Exception {
		M = read();
		N = read();
		L = new int[N];
		int sum = 0;
		for (int i = 0; i < N; i++) {
			L[i] = read();
			sum += L[i];
		}
		System.out.println(binarySearch());
	}

	private static int binarySearch() {
		int l = 1;
		int r = 1_000_000_000;
		while (l < r) {
			int m = (l + r) / 2;

			if (check(m)) {
				l = m + 1;
			} else {
				r = m;
			}
		}
		return r - 1;
	}

	private static boolean check(int m) {
		int count = 0;
		for (int i = 0; i < N; i++) {
			count += L[i] / m;
		}
		return count >= M;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
