import java.io.*;

public class Main { 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] s2 = br.readLine().toCharArray();
		char[] s1 = br.readLine().toCharArray();

		int[][] lcs = new int[s1.length + 1][s2.length + 1];

		for (int i = 1; i < lcs.length; i++) {
			for (int j = 1; j < lcs[0].length; j++) {
				if (s1[i - 1] == s2[j - 1]) {
					lcs[i][j] = lcs[i - 1][j - 1] + 1;
				} else {
					lcs[i][j] = Math.max(lcs[i][j - 1], lcs[i - 1][j]);
				}
			}
		}

		System.out.println(lcs[lcs.length - 1][lcs[0].length - 1]);
	}
}
