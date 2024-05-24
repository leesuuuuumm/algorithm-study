import java.io.*;

public class Main {

	static char[] arr;
	static int N;
	static int ans;
	static int[] count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = br.readLine().toCharArray();
		N = arr.length;
		count = new int[26];

		for (int i = 0; i < arr.length; i++) {
			count[arr[i] - 'a']++;
		}

		ans = 0;

		dfs(0, ' ');
		System.out.println(ans);

	}

	private static void dfs(int cnt, char c) {
		if (cnt == N) {
			ans++;
			return;
		}

		for (int i = 0; i < 26; i++) {
			if (count[i] == 0)
				continue;
			if (c != (char) (i + 'a')) {
				count[i]--;
				dfs(cnt + 1, (char) ('a' + i));
				count[i]++;
			}

		}
	}

}
