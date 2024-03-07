package boj;

public class Main_1011_AlphaCentauri {

	static int T;
	static int x, y;

	public static void main(String[] args) throws Exception {
		T = read();
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			x = read();
			y = read();
			int length = y - x;

			int max = (int)Math.sqrt(length);	// 소수점 버림

			if (max == Math.sqrt(length)) {
				sb.append(max * 2 - 1);
			} else if (length <= max * max + max) {
				sb.append(max * 2);
			} else {
				sb.append(max * 2 + 1);
			}
			sb.append("\n");
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
