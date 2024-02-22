import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		String[] ch = br.readLine().split("");

		int ans = Integer.MAX_VALUE;

		String s = "R";
		for (int k = 0; k < 2; k++) {
			boolean flag = false;
			int cnt = 0;

			if (k == 1) {
				s = "B";
			}
			for (int i = 0; i < N; i++) {
				if (!ch[i].equals(s)) {
					flag = true;
				}
				if (ch[i].equals(s) && flag) {
					cnt++;
				}
			}
			ans = Math.min(ans, cnt);
			cnt = 0;

			flag = false;
			for (int i = N - 1; i >= 0; i--) {
				if (!ch[i].equals(s)) {
					flag = true;
				}
				if (ch[i].equals(s) && flag) {
					cnt++;
				}
			}
			ans = Math.min(ans, cnt);
		}
		System.out.println(ans);

	}
}
