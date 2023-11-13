package boj;

import java.util.ArrayList;
import java.util.List;

public class Main_11729_하노이탑이동순서 {
	static int N;
	static List<int[]> list = new ArrayList<>();
	public static void main(String[] args) throws Exception {
	    N = read();
		hanoi(0, 1, 2, 3);
		StringBuilder result = new StringBuilder();
		result.append(list.size()).append("\n");
		for (int[] arr : list) {
			result.append(arr[0]).append(" ").append(arr[1]).append("\n");
		}
		System.out.print(result);
	}

	private static void hanoi(int cnt, int src, int mid, int dest) {
		if (cnt == N - 1) {
			list.add(new int[]{src, dest});
			return;
		}

		hanoi(cnt + 1, src, dest, mid);
		list.add(new int[]{src, dest});
		hanoi(cnt + 1, mid, src, dest);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
