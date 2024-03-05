import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] S = br.readLine().toCharArray();
		char[] P = br.readLine().toCharArray();

		int cnt = 0;
		int i = 0;

		while (i < P.length) {
			int max = 0;

			for (int j = 0; j < S.length; j++) {
				int idx = i;

				while (S[j] == P[idx]) {
					idx++;
					j++;

					if (j == S.length)
						break;
					if (idx == P.length)
						break;
				}
				max = Math.max(idx, max);

			}
			i = max;

			cnt++;
		}
		System.out.println(cnt);

	}
}
