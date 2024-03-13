import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		String[] arr = new String[N];
		int ans = 0;
		char[] f = null;
		e: for (int i = 0; i < N; i++) {
			arr[i] = br.readLine();
			if (i == 0) {
				f = arr[i].toCharArray();
				continue;
			}

			char[] s = arr[i].toCharArray();

			if (Math.abs(f.length - s.length) >= 2)
				continue;
			else {
				int cnt = 0;

				if (f.length <= s.length) {
					boolean[] v = new boolean[s.length];
					a: for (int j = 0; j < f.length; j++) {
						for (int k = 0; k < s.length; k++) {
							if (!v[k] && f[j] == s[k]) {
								cnt++;
								v[k] = true;
								continue a;
							}
						}
					}

					if (s.length - cnt <= 1) {
						ans++;

					}
				} else {
					boolean[] v = new boolean[f.length];
					a: for (int j = 0; j < s.length; j++) {
						for (int k = 0; k < f.length; k++) {
							if (!v[k] && s[j] == f[k]) {
								cnt++;
								v[k] = true;
								continue a;
							}
						}
					}
					if (f.length - cnt <= 1) {
						ans++;
					}
				}

			}
		}
		System.out.println(ans);

	}
}
