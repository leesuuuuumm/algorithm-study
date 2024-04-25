package boj;

import java.util.HashMap;
import java.util.Map;

public class Main_1354_무한수열2 {

	static long N, P, Q, X, Y;
	static Map<Long, Long> map = new HashMap<>();

	public static void main(String[] args) throws Exception {
		N = read();
		P = read();
		Q = read();
		X = read();
		Y = read();

		System.out.println(calculate(N));
	}

	private static long calculate(long n) {
		if (n <= 0)
			return 1;
		if (map.containsKey(n))
			return map.get(n);

		long result = calculate(n / P - X) + calculate(n / Q - Y);
		map.put(n, result);
		return result;
	}

	private static long read() throws Exception {
	    long c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
