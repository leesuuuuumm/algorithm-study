import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split("");
		String[] bomb = br.readLine().split("");

		Stack<String> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();

		e: for (int i = 0; i < s.length; i++) {
			stack.push(s[i]);
			if (stack.size() >= bomb.length) {
				int idx = 0;
				for (int j = stack.size() - bomb.length; j < stack.size(); j++) {
					if (!stack.get(j).equals(bomb[idx++]))
						continue e;
				}

				for (int j = 0; j < bomb.length; j++) {
					stack.pop();
				}
			}
		}
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(sb.length() == 0?"FRULA":sb.reverse());

	}
}
