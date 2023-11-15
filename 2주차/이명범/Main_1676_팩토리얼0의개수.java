package boj;

public class Main_1676_팩토리얼0의개수 {
	public static void main(String[] args) throws Exception {
	    int N = read();
		int result = 0;
		for (int i = 5; i <= N; i += 5) {
			int num = i;
			while (num % 5 == 0) {
				result++;
				num /= 5;
			}
		}
		System.out.println(result);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
