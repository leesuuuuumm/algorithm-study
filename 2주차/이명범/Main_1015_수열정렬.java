package boj;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_1015_수열정렬 {

	static class Number {
		int value;
		int index;

		public Number(int value, int index) {
			this.value = value;
			this.index = index;
		}
	}

	public static void main(String[] args) throws Exception {
	    int N = read();
		PriorityQueue<Number> pq = new PriorityQueue<>(new Comparator<Number>() {
			@Override
			public int compare(Number o1, Number o2) {
				int result = o1.value - o2.value;
				if (result == 0)
					result = o1.index - o2.index;
				return result;
			}
		});
		for (int i = 0; i < N; i++) {
			int value = read();
			pq.add(new Number(value, i));
		}
		int[] result = new int[N];
		for (int i = 0; i < N; i++) {
			Number cur = pq.poll();
			result[cur.index] = i;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(result[i]).append(" ");
		}
		System.out.println(sb);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
