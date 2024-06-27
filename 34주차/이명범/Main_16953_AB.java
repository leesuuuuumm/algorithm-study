package boj;

public class Main_16953_AB {

	static int A, B;

	public static void main(String[] args) throws Exception {
		A = read();
		B = read();

		int count = 0;
		while (A != B) {
			if (B == 0) {
				System.out.println(-1);
				return;
			}

			if (B % 2 == 0) {
				B /= 2;
			} else {
				if (B % 10 != 1) {
					System.out.println(-1);
					return;
				}
				B /= 10;
			}
			count++;
		}
		System.out.println(count + 1);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
