package boj;

public class Main_1976_여행가자 {

	static int N, M;
	static int[] groups;

	public static void main(String[] args) throws Exception {
		N = read();
		M = read();
		groups = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			groups[i] = i;
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int connected = read();
				if (connected == 0)
					continue;
				union(i, j);
			}
		}
		int group = find(read());

		for (int i = 0; i < M - 1; i++) {
			int v = read();

			if (find(v) != group) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}

	private static int find(int a) {
		if (groups[a] == a)
			return a;
		return groups[a] = find(groups[a]);
	}

	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if (pa == pb)
			return;
		
		groups[pb] = pa;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
