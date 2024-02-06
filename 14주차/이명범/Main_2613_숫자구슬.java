package boj;

public class Main_2613_숫자구슬 {

	private static final int MAX_N = 300;
	private static final int MAX_VALUE = 100;

	static int N, M, result;
	static int[] values, selected;

	public static void main(String[] args) throws Exception {
		input();
		binarySearch();
		select();
		output();
	}

	private static void output() {
		StringBuilder sb = new StringBuilder();
		sb.append(result).append("\n");
		for (int i = 0; i < M; i++) {
			sb.append(selected[i]).append(" ");
		}
		System.out.println(sb);
	}

	private static void select() {
		int sum = 0;
		int count = 0;
		int index = 0;
		for (int i = 0; i < N; i++) {
			if (sum + values[i] > result) {
				selected[index++] = count;

				sum = values[i];
				count = 1;
			} else {
				sum += values[i];
				count++;
			}
		}
		if (sum != 0) {
			selected[index] = count;
		}
		for (int i = M - 1; i >= 0; i--) {
			if (selected[i] != 0)
				break;

			selected[i]++;
			selected[index]--;

			if (selected[index] == 0)
				index--;
		}
	}

	private static void binarySearch() {
		int l = 0;
		int r = MAX_N * MAX_VALUE;
		while (l < r) {
			int m = (l + r) / 2;

			if (!check(m)) {
				l = m + 1;
			} else {
				r = m;
			}
		}
		result = r;
	}

	private static boolean check(int limit) {
		int count = 0;
		int sum = 0;
		for (int i = 0; i < N; i++) {
			if (values[i] > limit)
				return false;

			if (sum + values[i] > limit) {
				sum = values[i];
				count++;
			} else {
				sum += values[i];
			}
		}
		if (sum != 0) {
			count++;
		}
		return count <= M;
	}

	private static void input() throws Exception {
		N = read();
		M = read();
		values = new int[N];
		selected = new int[M];
		for (int i = 0; i < N; i++) {
			values[i] = read();
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
