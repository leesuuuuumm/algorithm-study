import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사다리타기 {
	static int K, N;
	static char[] answer;
	static int[] result;
	static char[][] map;
	static int notKnowRow;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		char[] ch = st.nextToken().toCharArray();
		result = new int[K + 1];
		for (int i = 1; i <= K; i++) {
			result[i] = ch[i - 1] - 'A' + 1;
		}
		map = new char[N][K];
		notKnowRow = 0;
		answer = new char[K];
		for (int i = 1; i < K; i++) {
			answer[i] = 'x';
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char[] in = st.nextToken().toCharArray();
			for (int j = 1; j < K; j++) {
				map[i][j] = in[j - 1];
				if (in[j - 1] == '?')
					notKnowRow = i;
			}
		}

		for (int i = 1; i <= K; i++) {
			dfs(i, 0, i, -1, ' ');
		}

		for (int i = 1; i < K; i++) {
			if (count < K)
				System.out.print('x');
			else
				System.out.print(answer[i]);
		}

	}

	public static void dfs(int v, int r, int c, int idx, char val) {
		if (r == N) {
			if (idx != -1 && result[c] == v) {
				count += 1;
				if (val == '-') {
					answer[idx] = val;
					if (idx - 1 >= 1)
						answer[idx - 1] = '*';
					if (idx + 1 < K) {
						answer[idx + 1] = '*';
					}
				} else {
					if (idx < K)
						answer[idx] = val;
					if (idx - 1 >= 1)
						answer[idx - 1] = val;
				}
			}
			return;
		}
		int left = c - 1;
		int right = c;
		if (r == notKnowRow) {
			if (left >= 1)
				dfs(v, r + 1, c - 1, left, '-');
			if (right < K)
				dfs(v, r + 1, c + 1, right, '-');
			dfs(v, r + 1, c, c, '*');
		} else {
			if (left >= 1 && map[r][left] == '-') {
				dfs(v, r + 1, c - 1, idx, val);
			} else if (right < K && map[r][right] == '-') {
				dfs(v, r + 1, c + 1, idx, val);
			} else
				dfs(v, r + 1, c, idx, val);
		}
	}
}
