package boj;

public class Main_3980_선발명단 {

	static int C, result;
	static int[][] abilities;

	public static void main(String[] args) throws Exception {
		C = read();
		StringBuilder output = new StringBuilder();
		for (int tc = 0; tc < C; tc++) {
			result = 0;
			abilities = new int[11][11];
			for (int i = 0; i < 11; i++) {
				for (int j = 0; j < 11; j++) {
					abilities[i][j] = read();
				}
			}
			recur(0, 0, 0);
			output.append(result).append("\n");
		}
		System.out.println(output);
	}

	private static void recur(int cnt, int score, int flag) {
		if (cnt == 11) {
			result = Math.max(result, score);
			return;
		}

		for (int i = 0; i < 11; i++) {
			if (abilities[cnt][i] == 0) continue;
			if ((flag & 1 << i) != 0) continue;
			recur(cnt + 1, score + abilities[cnt][i], flag | 1 << i);
		}
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
