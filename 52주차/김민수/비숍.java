import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 비숍 {
	static int N;
	static int[][] map;
	static boolean[][] diag1; // / 방향 대각선
	static boolean[][] diag2; // \ 방향 대각선
	static int blackAnswer = 0, whiteAnswer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		diag1 = new boolean[N][N];
		diag2 = new boolean[N][N];

		// 검은칸 탐색
		backtracking(-1, 0, true);
		// 흰칸 탐색
		diag1 = new boolean[N][N];
		diag2 = new boolean[N][N];
		backtracking(-1, 0, false);

		System.out.println(blackAnswer + whiteAnswer);
	}

	public static void backtracking(int idx, int depth, boolean isBlack) {
		if(isBlack) {
			blackAnswer = Math.max(blackAnswer, depth);
		} else {
			whiteAnswer = Math.max(whiteAnswer, depth);
		}

		for (int i = idx + 1; i < N * N; i++) {
			int r = i / N;
			int c = i % N;

			if (isBlack) {
				// 검은칸 탐색할 때는 (r+c)가 짝수인 칸만 처리
				if ((r + c) % 2 != 0) continue;
			} else {
				// 흰칸 탐색할 때는 (r+c)가 홀수인 칸만 처리
				if ((r + c) % 2 != 1) continue;
			}

			if (map[r][c] == 1 && (!diag1[r][c] && !diag2[r][c])) {
				makeBShopRoute(r, c, true);
				backtracking(i, depth + 1, isBlack);
				makeBShopRoute(r, c, false);
			}
		}
	}

	public static boolean isInRange(int nx, int ny) {
		return (nx >= 0 && ny >= 0 && nx < N && ny < N);
	}

	public static void makeBShopRoute(int x, int y, boolean value) {
		// 오른쪽 아래 대각선
		int nx = x + 1;
		int ny = y + 1;
		while (nx < N && ny < N) {
			diag2[nx][ny] = value;
			nx += 1;
			ny += 1;
		}

		// 왼쪽 아래 대각선
		nx = x + 1;
		ny = y - 1;
		while (nx < N && ny >= 0) {
			diag1[nx][ny] = value;
			nx += 1;
			ny -= 1;
		}

		// 오른쪽 위 대각선
		nx = x - 1;
		ny = y + 1;
		while (nx >= 0 && ny < N) {
			diag1[nx][ny] = value;
			nx -= 1;
			ny += 1;
		}

		// 왼쪽 위 대각선
		nx = x - 1;
		ny = y - 1;
		while (nx >= 0 && ny >= 0) {
			diag2[nx][ny] = value;
			nx -= 1;
			ny -= 1;
		}
	}
}
