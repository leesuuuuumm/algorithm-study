package boj;

public class Main_1493_박스채우기 {

	static int l, w, h, N;
	static int[] cubes;

	public static void main(String[] args) throws Exception {
		l = read();
		w = read();
		h = read();
		N = read();
		cubes = new int[20];
		for (int i = 0; i < N; i++) {
			int A = read();
			int B = read();
			cubes[A] = B;
		}
		System.out.println(recur(l, w, h));
	}

	private static int recur(int length, int width, int height) {
		if (length == 0 || width == 0 || height == 0)
			return 0;

		int result = 0;
		while (height > 0) {
			int size = getSize(length, width, height);
			while (cubes[size] == 0) {
				if (--size < 0)
					return -1;
			}
			cubes[size]--;
			result++;

			int output = recur(length - (1 << size), 1 << size, 1 << size);
			if (output == -1)
				return -1;
			result += output;

			output = recur(1 << size, width - (1 << size), 1 << size);
			if (output == -1)
				return -1;
			result += output;

			output = recur(length - (1 << size), width - (1 << size), 1 << size);
			if (output == -1)
				return -1;
			result += output;

			height -= 1 << size;
		}
		return result;
	}



	private static int getSize(int length, int width, int height) {
		int size = Math.min(log2(length), log2(width));
		size = Math.min(size, log2(height));
		return size;
	}

	static int log2(int value) {
		return (int) (Math.log10(value) / Math.log10(2));
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
