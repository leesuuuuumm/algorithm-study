package boj;

public class Main_10987_나머지합 {

	static int N, M;
	static long result;
	static long[] sum, count;

	public static void main(String[] args) throws Exception {
		N = (int) read();
		M = (int) read();
		sum = new long[N + 1];
		count = new long[M];

		for (int i = 1; i < N + 1; i++) {
			sum[i] = (sum[i - 1] + read()) % M;
			if(sum[i] == 0) {
				result++;
			}
			count[(int) sum[i]]++;
		}
		for (int i = 0; i < M; i++) {
			if (count[i] > 1) {
				result += (count[i] * (count[i] - 1) / 2);
			}
		}
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
