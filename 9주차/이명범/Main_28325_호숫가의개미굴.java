package boj;

public class Main_28325_호숫가의개미굴 {

	static int N;
	static long[] nums;

	public static void main(String[] args) throws Exception {
		N = (int) read();
		nums = new long[N];
		long result = 0;
		for (int i = 0; i < N; i++) {
			nums[i] = read();

			if (nums[i] > 0) {
				result += nums[i];
			}
		}
		boolean[] flag = new boolean[N];
		long aCount = 0;
		long bCount = 0;
		for (int i = 0; i < N; i++) {
			if (nums[i] > 0 || flag[(i + N - 1) % N] || flag[(i + 1) % N])
				continue;

			aCount++;
			flag[i] = true;
		}
		flag = new boolean[N];
		for (int i = N - 1; i >= 0; i--) {
			if (nums[i] > 0 || flag[(i + N - 1) % N] || flag[(i + 1) % N])
				continue;

			bCount++;
			flag[i] = true;
		}
		result += Math.max(aCount, bCount);
		System.out.println(result);
	}

	private static long read() throws Exception {
	    long c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
