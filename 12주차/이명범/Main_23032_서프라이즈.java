package boj;

public class Main_23032_서프라이즈 {

	static int N;
	static int[] nums, sum;

	public static void main(String[] args) throws Exception {
		N = read();
		nums = new int[N + 1];
		sum = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			nums[i] = read();
			sum[i] = sum[i - 1] + nums[i];
		}
		int min = Integer.MAX_VALUE;
		int maxSum = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				int bs = binarySearch(i, j);

				if (min > bs || (min == bs && maxSum < sum[j] - sum[i - 1])) {
					min = bs;
					maxSum = sum[j] - sum[i - 1];
				}
			}
		}
		System.out.println(maxSum);
	}

	private static int binarySearch(int l, int r) {
		int min = Integer.MAX_VALUE;
		int tl = l;
		int tr = r;
		while (tl < tr) {
			int m = (tl + tr) / 2;

			int subtract = subtract(l, m, r);
			min = Math.min(min, Math.abs(subtract));

			if (subtract < 0)
				tl = m + 1;
			else if (subtract > 0)
				tr = m;
			else
				break;
		}
		return min;
	}

	private static int subtract(int l, int m, int r) {
		int lSum = sum[m] - sum[l - 1];
		int rSum = sum[r] - sum[m];

		return lSum - rSum;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
