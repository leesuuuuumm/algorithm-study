import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Deque<String> dq = new ArrayDeque<>();
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int bt = Integer.parseInt(st.nextToken());
			if (dq.isEmpty() && bt == 3)
				continue;

			if (bt == 1) {
				dq.addLast(st.nextToken());
				stack.push(1);
			} else if (bt == 2) {
				dq.addFirst(st.nextToken());
				stack.push(2);
			} else if (bt == 3) {
				if (stack.peek() == 1) {
					dq.pollLast();
				} else if (stack.peek() == 2) {
					dq.pollFirst();
				}
				stack.pop();
			}
		}

		if (dq.size() == 0) {
			System.out.println(0);
			return;
		}
		StringBuilder sb = new StringBuilder();
		while (!dq.isEmpty()) {
			sb.append(dq.poll());
		}
		System.out.println(sb);

	}
}
