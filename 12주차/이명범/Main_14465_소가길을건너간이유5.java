package boj;

public class Main_14465_소가길을건너간이유5 {

	static int N, K, B;
	static boolean[] isBroken;

	public static void main(String[] args) throws Exception {
		N = read();
		K = read();
		B = read();
		isBroken = new boolean[N + 1];
		for (int i = 0; i < B; i++) {
			isBroken[read()] = true;
		}
		int l = 1;
		int r = 1;
		int min = Integer.MAX_VALUE;
		int sum = 0;
		int count = 0;
		while (r <= N) {
			sum++;
			count += isBroken[r] ? 1 : 0;

			if (sum == K){
				min = Math.min(min, count);

				if (isBroken[l++])
					count--;

				sum--;
			}
			r++;
		}
		System.out.println(min);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
