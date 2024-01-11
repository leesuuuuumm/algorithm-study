package boj;

public class Main_13711_LCS4 {

	static int N;
	static int[] A, B, indexes;

	public static void main(String[] args) throws Exception {
		N = read();
		A = new int[N + 1];
		B = new int[N + 1];
		indexes = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			A[i] = read();
		}
		for (int i = 1; i <= N; i++) {
			B[i] = read();
		}
		for (int i = 1; i <= N; i++) {
			indexes[A[i]] = i;
		}
		int[] list = new int[N + 1];
		int count = 1;
		list[1] = indexes[B[1]];
		for (int i = 2; i <= N; i++) {
			if (list[count] < indexes[B[i]]) {
				list[++count] = indexes[B[i]];
				continue;
			}
			int l = 0;
			int r = count;

			while (l < r) {
				int m = (l + r) / 2;

				if (list[m] <= indexes[B[i]]) {
					l = m + 1;
				} else {
					r = m;
				}
			}
			list[r] = indexes[B[i]];
		}
		System.out.println(count);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
