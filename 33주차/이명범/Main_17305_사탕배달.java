package boj;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main_17305_사탕배달 {

	static class Candy {
		int t;
		int s;

		public Candy(int t, int s) {
			this.t = t;
			this.s = s;
		}
	}

	static int N, W;
	static List<Integer> threeCandies;
	static List<Integer> fiveCandies;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		input();
		threeCandies.sort(Comparator.reverseOrder());
		fiveCandies.sort(Comparator.reverseOrder());
		long[] threeSum = new long[threeCandies.size() + 1];
		long[] fiveSum = new long[fiveCandies.size() + 1];
		for (int i = 1; i <= threeCandies.size(); i++) {
			threeSum[i] = threeSum[i - 1] + threeCandies.get(i - 1);
		}
		for (int i = 1; i <= fiveCandies.size(); i++) {
			fiveSum[i] = fiveSum[i - 1] + fiveCandies.get(i - 1);
		}
		int s = Math.min(W / 3, threeSum.length - 1);
		long result = threeSum[s];
		for (int i = s; i >= 0; i--) {
			int o = Math.min((W - 3 * i) / 5, fiveSum.length - 1);

			result = Math.max(result, threeSum[i] + fiveSum[o]);
		}
		System.out.println(result);
	}

	private static void input() throws Exception {
		N = read();
		W = read();
		threeCandies = new ArrayList<>();
		fiveCandies = new ArrayList<>();
		dp = new int[W + 1];
		for (int i = 0; i < N; i++) {
			int t = read();
			int s = read();

			if (t == 3)
				threeCandies.add(s);
			else
				fiveCandies.add(s);
		}
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
