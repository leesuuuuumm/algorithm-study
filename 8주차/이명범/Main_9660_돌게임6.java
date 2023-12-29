package boj;

public class Main_9660_돌게임6 {
	public static void main(String[] args) throws Exception {
		long N = read();
		if (N % 7 == 0 || N % 7 == 2) {
			System.out.println("CY");
			return;
		}
		System.out.println("SK");
	}

	private static long read() throws Exception {
	    long c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
