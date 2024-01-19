import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		Stack<Long> stack = new Stack<>();

		stack.push(arr[0]);

		long answer = 0;

		for (int i = 1; i < N; i++) {

			while (!stack.empty() && stack.peek() <= arr[i]) {
				stack.pop();
			}
			stack.push(arr[i]);
			answer += stack.size() - 1;
		}
		System.out.println(answer);

	}
}
