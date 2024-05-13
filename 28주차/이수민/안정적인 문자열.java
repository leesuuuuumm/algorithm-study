import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int k = 1;
		while (true) {
			char[] c = br.readLine().toCharArray();
			if (c[0] == '-')
				break;
			Stack<Character> st = new Stack<>();
			int cnt = 0;

			for (int i = 0; i < c.length; i++) {
				if (st.isEmpty()) {
					if (c[i] == '}') {
						cnt++;
					}
					st.push('{');
				} else if (st.peek() == '{' && c[i] == '}') {
					st.pop();
				} else {
					st.push(c[i]);
				}
			}
			if (!st.isEmpty()) {
				cnt += st.size() / 2;
			}
			sb.append(k++).append(". ").append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}

}
