import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
	
		int[] arr = new int[N];
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int num = 0;
		StringBuilder sb = new StringBuilder();
		b: for (int i = 0; i < N; i++) {
			while (true) {
				if (num > N)
					break b;

				if (stack.isEmpty() || stack.peek() != arr[i]) {
					stack.add(++num);
					sb.append("+").append("\n");
				} else if (arr[i] == stack.peek()) {
					stack.pop();
					sb.append("-").append("\n");
					break;
				}
			}
		}
		
		System.out.print(stack.isEmpty() ? sb.toString() : "NO");
	}
}
