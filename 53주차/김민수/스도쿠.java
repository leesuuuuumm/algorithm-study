import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//빡구현

public class 스도쿠 {
	static int[][] map;
	static boolean end;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9][9];
		for (int i = 0; i < 9; i++) {
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				map[i][j] = ch[j] - '0';
			}
		}

		dfs(0);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}

	public static void dfs(int depth) {
		if (depth == 81) {
			end = true;
			return;
		}
		int r = depth / 9;
		int c = depth % 9;

		if (map[r][c] != 0) {
			dfs(depth + 1);
		} else {
			for (int i = 1; i <= 9; i++) {
				if (isValid(r, c, i)) {
					map[r][c] = i;
					dfs(depth + 1);
					if (end)
						return;
					map[r][c] = 0;
				}
			}
		}
	}

	public static boolean isValid(int r, int c, int val) {
		for (int i = 0; i < 9; i++) {
			if (map[i][c] == val || map[r][i] == val)
				return false;
		}
		int rr = r / 3 * 3;
		int cc = c / 3 * 3;
		for (int i = rr; i < rr + 3; i++) {
			for (int j = cc; j < cc + 3; j++) {
				if (map[i][j] == val) {
					return false;
				}
			}
		}
		return true;
	}
}
