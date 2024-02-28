package boj;

public class Main_12931_두배더하기 {

	static int N;
	static int[] nums;

	public static void main(String[] args) throws Exception {
		N = read();
		nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = read();
		}

		int result = 0;
		while (true) {
			int mod = check();

			if (mod == 0) {
				for (int i = 0; i < N; i++) {
					if (nums[i] % 2 == 1) {
						nums[i]--;
						break;
					}
				}
			} else if (mod == 1) {
				for (int i = 0; i < N; i++) {
					nums[i] /= 2;
				}
			} else {
				break;
			}
			result++;
		}
		System.out.println(result);
	}

	// 홀수가 있을때 : 0
	// 전부 짝수일 때 : 1
	// 전부 0일 때 : 2
	private static int check() {
		boolean hasAnyPositiveNumber = false;
		for (int i = 0; i < N; i++) {
			if (nums[i] % 2 == 1)
				return 0;
			if (nums[i] != 0)
				hasAnyPositiveNumber = true;
		}

		return hasAnyPositiveNumber ? 1 : 2;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
