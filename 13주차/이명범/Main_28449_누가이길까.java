package boj;

import java.util.Arrays;

public class Main_28449_누가이길까 {

	static int N, M;
	static int[] A, B;

	public static void main(String[] args) throws Exception {
		N = read();
		M = read();
		A = new int[N];
		B = new int[M];
		for (int i = 0; i < N; i++) {
			A[i] = read();
		}
		for (int i = 0; i < M; i++) {
			B[i] = read();
		}
		Arrays.sort(B);
		long[] result = new long[3];
		for (int a : A) {
			int l = lower(a);
			int u = upper(a);
			result[0] += l;
			result[1] += M - u;
			result[2] += u - l;
		}
		System.out.println(result[0] + " " + result[1] + " " + result[2]);
	}

	private static int lower(int score) {
		int l = 0;
		int r = M;

		while (l < r) {
			int m = (l + r) / 2;

			if (B[m] < score) {
				l = m + 1;
			} else {
				r = m;
			}
		}
		return r;
	}

	private static int upper(int score) {
		int l = 0;
		int r = M;

		while (l < r) {
			int m = (l + r) / 2;

			if (B[m] <= score) {
				l = m + 1;
			} else {
				r = m;
			}
		}
		return r;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
