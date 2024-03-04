package boj;

import java.util.Arrays;

public class Main_13164_행복유치원 {

	static int N, K;
	static int[] num;

	public static void main(String[] args) throws Exception {
		N = read();
		K = read();
		num = new int[N];
		for (int i = 0; i < N; i++) {
			num[i] = read();
		}
		int[] diff = new int[N - 1];
		for (int i = 0; i < N - 1; i++) {
			diff[i] = num[i + 1] - num[i];
		}
		Arrays.sort(diff);
		int result = num[N - 1] - num[0];
		for (int i = 0; i < K - 1; i++) {
			result -= diff[N - 2 - i];
		}
		System.out.println(result);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
