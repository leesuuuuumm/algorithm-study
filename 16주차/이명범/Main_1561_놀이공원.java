package boj;

public class Main_1561_놀이공원 {

	static int N, M;
	static int[] times;

	public static void main(String[] args) throws Exception {
		N = read();
		M = read();
		times = new int[M + 1];
		for (int i = 1; i <= M; i++) {
			times[i] = read();
		}
		long time = binarySearch();
		long sum = 0;
		for (int i = 1; i <= M; i++) {
			if (time == 0)
				break;
			sum += (time - 1) / times[i] + 1;
		}
		int result = 0;
		for (int i = 1; i <= M; i++) {
			if (time % times[i] == 0)
				sum++;

			if (sum == N) {
				result = i;
				break;
			}
		}
		System.out.print(result);
	}

	private static long binarySearch() {
		long l = 0;
		long r = 2_000_000_000L * 30;

		while (l < r) {
			long m = (l + r) / 2;

			if (check(m))
				r = m;
			else
				l = m + 1;
		}
		return r;
	}

	private static boolean check(long m) {
		long sum = 0;
		for (int i = 1; i <= M; i++) {
			sum += m / times[i] + 1;
		}
		return sum >= N;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
