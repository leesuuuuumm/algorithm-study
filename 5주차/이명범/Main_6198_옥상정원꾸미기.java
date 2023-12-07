package boj;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main_6198_옥상정원꾸미기 {
	static int N;

	public static void main(String[] args) throws Exception {
		N = read();
		Deque<Integer> stack = new ArrayDeque<>();
		long result = 0;
		for (int i = 0; i < N; i++) {
			int value = read();
			while (!stack.isEmpty() && stack.peek() <= value) {
				stack.pop();
			}
			result += stack.size();
			stack.push(value);
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
