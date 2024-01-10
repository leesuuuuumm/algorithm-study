package boj;

public class Main_1083_소트 {

	static int N, S;
	static int[] nums;

	public static void main(String[] args) throws Exception {
		N = read();
		nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = read();
		}
		S = read();
		for (int i = 0; i < N - 1; i++) {
			if (S <= 0)
				break;

			int maxIdx = 0;
			int max = 0;
			for (int j = i; j <= Math.min(i + S, N - 1); j++) {
				if (max < nums[j]) {
					max = nums[j];
					maxIdx = j;
				}
			}
			for (int j = maxIdx; j > i; j--) {
				int temp = nums[j];
				nums[j] = nums[j - 1];
				nums[j - 1] = temp;
				S--;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int num : nums) {
			sb.append(num).append(" ");
		}
		System.out.print(sb);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
