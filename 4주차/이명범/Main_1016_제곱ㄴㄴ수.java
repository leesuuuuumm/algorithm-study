package boj;

import java.util.Arrays;

public class Main_1016_제곱ㄴㄴ수 {
	static long min, max;
	static int[] visit;

	public static void main(String[] args) throws Exception {
	    min = read();
		max = read();
		visit = new int[(int) (max - min) + 1];

		for (long i = 2; i * i <= max; i++) {
			long start = min % (i * i) == 0 ? min / (i * i) : (min / (i * i)) + 1;

			for (long j = start; j * i * i <= max; j++) {
				visit[(int) (j * i * i - min)] = 1;
			}
		}
		System.out.println((int) (max - min) + 1 - Arrays.stream(visit).sum());
	}

	private static long read() throws Exception {
	    long c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
