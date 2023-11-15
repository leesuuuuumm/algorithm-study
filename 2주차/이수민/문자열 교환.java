import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringBuilder sb = new StringBuilder();

		int a = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'a')
				a++;
			sb.append(s.charAt(i));
		}
		for (int i = 0; i < a - 1; i++) {
			sb.append(s.charAt(i));
		}
		String[] str = sb.toString().split("");

		int ans = 1000;
		for (int i = 0; i < str.length - a + 1; i++) {
			int cnt = 0;
			for (int j = i; j < i + a; j++) {
				if (str[j].equals("b"))
					cnt++;

			}
			ans = Math.min(ans, cnt);
		}
		System.out.println(ans);

	}
}
