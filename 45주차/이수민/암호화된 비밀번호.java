import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 0; t < T; t++) {
			char[] en = br.readLine().toCharArray();
			char[] an = br.readLine().toCharArray();

			int[] err = new int[26];
			int[] arr = new int[26];

			for (int i = 0; i < an.length; i++) {
				arr[an[i] - 'a']++;
			}

			for (int i = 0; i < an.length; i++) {
				err[en[i] - 'a']++;
			}
			int s = 0;
			int e = an.length - 1;

			boolean f = false;
			while (true) {
				if (Arrays.equals(arr, err)) {
					f = true;
					break;
				}
				err[en[s] - 'a']--;

				s++;
				e++;

				if (e == en.length)
					break;
				err[en[e] - 'a']++;

			}

			sb.append(f ? "YES" : "NO").append("\n");

		}
		System.out.println(sb);

	}

}
