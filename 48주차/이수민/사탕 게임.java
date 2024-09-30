import java.io.*;
import java.util.*;

public class Main {
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static ArrayList<Integer> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N];

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			map[r] = st.nextToken().toCharArray();
		}
		list = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				getCandy(map, r, c, N);
			}

		}

		Collections.sort(list, Collections.reverseOrder());
		System.out.println(list.get(0));
	}

	private static void getCandy(char[][] map, int r, int c, int N) {
		for (int d = 0; d < 4; d++) {
			int nr = dr[d] + r;
			int nc = dc[d] + c;
			

			if (!check(nr, nc, N)) {
				continue;
			}

			char tmp = map[r][c];
			map[r][c] = map[nr][nc];
			map[nr][nc] = tmp;

			char k = map[0][c];
			int cnt = 0;
			int max = 0;

			for (int i = 0; i < N; i++) {
				if (k == map[i][c]) {
					cnt++;
				} else {
					cnt = 1;
					k = map[i][c];
				}
				max = Math.max(cnt, max);
			}

			checkTheList(max);

			k = map[r][0];

			cnt = 0;
			max = 0;

			for (int j = 0; j < N; j++) {

				if (k == map[r][j]) {
					cnt++;

				} else {
					cnt = 1;
					k = map[r][j];
				}
				max = Math.max(cnt, max);
			}
			checkTheList(max);

			map[nr][nc] = map[r][c];
			map[r][c] = tmp;
		}
	}
    private static void checkTheList(int max) {
		if (!list.contains(max)) {
			list.add(max);
		}
	}

	private static boolean check(int nr, int nc, int N) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
