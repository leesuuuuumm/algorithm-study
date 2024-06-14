package boj;

public class Main_11501_주식 {

	static int T, N;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		T = read();
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			N = read();
			arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = read();
			}
			long result = 0;
			int max = 0;
			for (int i = N - 1; i >= 0; i--) {
				if (arr[i] > max)
					max = arr[i];
				if (arr[i] < max)
					result += max - arr[i];
			}
			sb.append(result).append("\n");
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
