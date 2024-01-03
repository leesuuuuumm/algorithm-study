package boj;

public class Main_14267_회사문화1 {

	static int n, m;
	static int[] parents;
	static int[] scores;

	public static void main(String[] args) throws Exception {
		n = read();
		m = read();
		parents = new int[n + 1];
		scores = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parents[i] = read();

			if (i == 1)
				parents[i] = -1;
		}
		for (int i = 0; i < m; i++) {
			int num = read();
			int score = read();
			scores[num] += score;
		}
		for (int i = 2; i <= n; i++) {
			scores[i] += scores[parents[i]];
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			sb.append(scores[i]).append(" ");
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
