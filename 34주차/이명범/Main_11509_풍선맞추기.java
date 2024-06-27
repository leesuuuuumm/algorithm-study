package boj;

public class Main_11509_풍선맞추기 {

	static int N;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		N = read();
		arr = new int[1_000_002];
		for (int i = 0; i < N; i++) {
			int num = read();

			if (arr[num + 1] > 0)
				arr[num + 1]--;

			arr[num]++;
		}
		int result = 0;
		for (int i = 0; i < 1_000_001; i++) {
			result += arr[i];
		}
		System.out.println(result);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
