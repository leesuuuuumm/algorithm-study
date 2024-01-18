import java.io.*;
import java.util.*;

public class Main {

	static int[][] lcs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str2 = br.readLine().split("");
		String[] str1 = br.readLine().split("");
		lcs = new int[str1.length + 1][str2.length + 1];
		for (int i = 1; i <= str1.length; i++) {

			for (int j = 1; j <= str2.length; j++) {
				if (str1[i - 1].equals(str2[j - 1])) {
					lcs[i][j] = lcs[i - 1][j - 1] + 1;
				} else {
					lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
				}
			}
		}
		System.out.println(lcs[str1.length][str2.length]);
		int r = str1.length;
		int c = str2.length;
		StringBuilder sb = new StringBuilder();
		boolean f = false;
		while (r > 0 && c > 0) {

			if (lcs[r - 1][c] == lcs[r][c]) {
				r = r - 1;
				f = true;
			} else if (lcs[r][c - 1] == lcs[r][c]) {
				c = c - 1;
				f = false;
			} else {
				if (f) {
					sb.append(str1[r-1]);
				} else {
					sb.append(str2[c-1]);
				}
				r--;
				c--;
			}
		}
		System.out.println(sb.reverse().toString());

	}
}
