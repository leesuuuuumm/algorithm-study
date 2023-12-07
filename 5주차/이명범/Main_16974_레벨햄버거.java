package boj;

public class Main_16974_레벨햄버거 {

	static int N;
	static long X;
	static long[] layer;
	static long[] patties;

	public static void main(String[] args) throws Exception {
		input();
		System.out.println(recur(N, X - 1));
	}

	private static long recur(int level, long limit) {
		if (level == 0)
			return 1;

		long sum = 0;
		long centerIndex = (layer[level] - 1) / 2;

		if (limit >= 1) {
			if (limit >= centerIndex - 1)
				sum += patties[level - 1];
			else
				sum += recur(level - 1, Math.min(limit - 1, layer[level - 1] - 1));
		}

		if (limit >= centerIndex)
			sum++;

		if (limit >= centerIndex + 1)
			sum += recur(level - 1, Math.min(limit - (centerIndex + 1), layer[level - 1] - 1));

		return sum;
	}

	private static void input() throws Exception {
		N = (int) read();
		X = read();
		layer = new long[N + 1];
		patties = new long[N + 1];
		layer[0] = 1;
		patties[0] = 1;
		for (int i = 1; i <= N; i++) {
			layer[i] = layer[i - 1] * 2 + 3;
			patties[i] = patties[i - 1] * 2 + 1;
		}
	}

	private static long read() throws Exception {
	    long c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
