package boj;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

public class Main_1863_스카이라인쉬운거 {
	static class Skyline {
		int p;
		int h;

		public Skyline(int p, int h) {
			this.p = p;
			this.h = h;
		}
	}
	static int N, result;
	static List<Skyline> skylines;
	static Deque<Integer> stack;

	public static void main(String[] args) throws Exception {
		input();
		for (Skyline skyline : skylines) {
			while (!stack.isEmpty() && stack.peek() > skyline.h) {
				stack.pop();
				result++;
			}

			if (skyline.h == 0)
				continue;
			if (stack.isEmpty() || stack.peek() < skyline.h)
				stack.push(skyline.h);
		}
		System.out.println(result + stack.size());
	}

	private static void input() throws Exception {
		N = read();
		skylines = new ArrayList<>();
		stack = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			int p = read();
			int w = read();
			skylines.add(new Skyline(p, w));
		}
		skylines.sort(Comparator.comparing(o -> o.p));
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
