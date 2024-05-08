import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] c = br.readLine().toCharArray();
		int ans = 0;
		Stack<Character> st = new Stack<>();
		int cnt = 0;
		for (int i = 0; i < c.length; i++) {
			if (st.isEmpty() || st.peek() == c[i]) {
				st.push(c[i]);
				cnt++;
			} else if (st.peek() != c[i]) {
				st.pop();
				ans = Math.max(ans, cnt);
				cnt--;
			}
		}
		System.out.println(st.size() > 0 ? -1 : ans);

	}

}
