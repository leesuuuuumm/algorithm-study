import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		int ans = 0;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			while (!stack.isEmpty() && stack.peek() > y) {
				ans++;
				stack.pop();
			}
			if (!stack.isEmpty() && stack.peek() == y)
				continue;
			stack.push(y);

		}
		while (!stack.isEmpty()) {
			if (stack.peek() > 0)
				ans++;
			stack.pop();
		}
		System.out.println(ans);

	}
}
