package boj;

public class Main_25634_전구상태뒤집기 {

	static int N, result;
	static int[] nums, switches;

	public static void main(String[] args) throws Exception {
		N = read();
		result = 0;
		nums = new int[N];
		switches = new int[N];

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			nums[i] = read();
			min = Math.min(min, nums[i]);
		}

		int value = 0;
		int sum = 0;
		for (int i = 0; i < N; i++) {
			switches[i] = read();
			result += nums[i] * switches[i];
		}
		for (int i = 0; i < N; i++) {
			if (switches[i] == 1)
				sum -= nums[i];
			else
				sum += nums[i];

			value = Math.max(value, sum);
			if (sum >= 0)
				continue;
			sum = 0;
		}

		if (value > 0)
			System.out.println(result + value);
		else
			System.out.println(result - min);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
