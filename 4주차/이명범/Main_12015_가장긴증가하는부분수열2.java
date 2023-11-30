package boj;

public class Main_12015_가장긴증가하는부분수열2 {

	static int N;
	static int[] nums;
	static int[] lis;

	public static void main(String[] args) throws Exception {
		input();
		int l = 0;
		int r = 0;
		for (int i = 1; i < N; i++) {
			if (lis[r] < nums[i]) {
				lis[++r] = nums[i];
				continue;
			}
			int index = binarySearch(l, r, nums[i]);
			lis[index] = nums[i];
		}
		System.out.println(r + 1);
	}

	private static int binarySearch(int l, int r, int value) {
		while (l < r) {
			int m = (l + r) / 2;

			if (lis[m] == value)
				return m;

			if (lis[m] < value)
				l = m + 1;
			else
				r = m;
		}
		return l;
	}

	private static void input() throws Exception {
		N = read();
		nums = new int[N];
		lis = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = read();
		}
		lis[0] = nums[0];
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
