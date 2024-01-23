package boj;

import java.util.Arrays;

public class Main_22988_재활용캠페인 {
	static int N;
	static long X;
	static long[] containers;

	public static void main(String[] args) throws Exception {
		input();
		int l = 0;
		int r = N - 1;
		int result = 0;
		int count = 0;
		while (r >= 0) {
			if (containers[r] >= X) {
				result++;
				count++;
				r--;
				continue;
			}
			break;
		}
		while (l < r) {
			if (containers[l] + containers[r] + X / 2 >= X) {
				result++;
				count += 2;
				r--;
			}
			l++;
		}
		result += (N - count) / 3;
		System.out.println(result);
	}

	private static void input() throws Exception {
		N = (int) read();
		X = read() * 2;
		containers = new long[N];
		for (int i = 0; i < N; i++) {
			containers[i] = read() * 2;
		}
		Arrays.sort(containers);
	}

	private static long read() throws Exception {
	    long c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
