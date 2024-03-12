import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] str = new String[N];
		for (int i = 0; i < N; i++) {
			str[i] = br.readLine();
		}
		int len = 0;
		String S = "";
		String T = "";
		for (int i = 0; i < N; i++) {
			String s1 = str[i];
			for (int j = i + 1; j < N; j++) {
				int cnt = 0;
				String s2 = str[j];

				int size = Math.min(s1.length(), s2.length());

				for (int k = 0; k < size; k++) {
					if (s1.charAt(k) == s2.charAt(k))
						cnt++;
					else
						break;
				}

				if (len < cnt) {
					S = s1;
					T = s2;
					len = cnt;

				}

			}
		}
		System.out.println(S);
		System.out.println(T);

	}
}
