import java.io.*;
import java.util.*;

public class Main {
	static class Top {
		int num;
		int height;

		public Top(int num, int height) {
			this.num = num;
			this.height = height;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] comm = new int[N];
		for (int i = 0; i < N; i++) {
			comm[i] = Integer.parseInt(st.nextToken());
		}

		StringBuilder sb = new StringBuilder();
		Stack<Top> stack = new Stack<>();

		for (int i = 0; i < N; i++) {
			if (stack.isEmpty()) {
				sb.append("0 ");
				stack.push(new Top(i + 1, comm[i]));
			} else {
				while (true) {
					if (stack.isEmpty()) {
						sb.append("0 ");
						stack.push(new Top(i + 1, comm[i]));
						break;
					}

					Top top = stack.peek();

					if (top.height > comm[i]) {
						sb.append(top.num + " ");
						stack.push(new Top(i + 1, comm[i]));
						break;
					} else {
						stack.pop();
					}
				}
			}

		}
		System.out.println(sb.toString());
	}
}
