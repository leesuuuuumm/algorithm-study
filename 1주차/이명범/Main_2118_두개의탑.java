package boj;

public class Main_2118_두개의탑 {

	static int N, mid;
	static int[] distanceOfTower;
	static int result = 0;

	public static void main(String[] args) throws Exception {
		input();
		solve();
	}

	private static void solve() {
		int l = 0;
		int r = 0;
		int distance = 0;

		while (l < N) {
			if (distance == mid) {
				break;
			} else if (distance < mid) {
				distance += distanceOfTower[r];
				r = (r + 1) % N;
			} else {
				distance -= distanceOfTower[l++];
			}

			if (Math.abs(mid - distance) < Math.abs(mid - result))
				result = distance;
		}
		
		System.out.println(result);
	}

	private static void input() throws Exception {
		N = read();
		distanceOfTower = new int[N];
		int sum = 0;
		for (int i = 0; i < N; i++) {
			distanceOfTower[i] = read();
			sum += distanceOfTower[i];
		}
		mid = sum / 2;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
