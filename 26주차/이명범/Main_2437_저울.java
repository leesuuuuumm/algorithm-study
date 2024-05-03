package boj;

import java.util.Arrays;

public class Main_2437_저울 {

	static int N;
	static int[] nums;

	public static void main(String[] args) throws Exception {
		N = read();
		nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = read();
		}
		Arrays.sort(nums);

		if (nums[0] > 1) {
			System.out.println(1);
			return;
		}

		int sum = nums[0];
		for (int i = 1; i < N; i++) {
			if (sum + 1 >= nums[i])
				sum = sum + nums[i];
			else
				break;
		}
		System.out.println(sum + 1);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
