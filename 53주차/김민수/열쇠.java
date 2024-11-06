import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class 열쇠 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			char[][] map = new char[h][w];
			for (int j = 0; j < h; j++) {
				char[] ch = br.readLine().toCharArray();
				for (int k = 0; k < w; k++) {
					map[j][k] = ch[k];
				}
			}
			char[] key = br.readLine().toCharArray();

			System.out.println(solve(h, w, map, key));
		}
	}

	public static int solve(int h, int w, char[][] input, char[] keyInput) {
		int answer = 0;
		HashSet<Character> key = new HashSet<>();
		if (!(keyInput.length == 1 && keyInput[0] == '0')) {
			for (int i = 0; i < keyInput.length; i++) {
				key.add(keyInput[i]);
			}
		}
		boolean[][] visited = new boolean[h][w];
		ArrayList<int[]>[] doors = new ArrayList[26];
		for (int i = 0; i < 26; i++) {
			doors[i] = new ArrayList<>();
		}
		Queue<int[]> que = new ArrayDeque<>();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (i == 0 || i == h - 1 || j == 0 || j == w - 1) {
					char val = input[i][j];
					if (val == '$') {
						answer += 1;
						que.add(new int[] {i, j});
						visited[i][j] = true;
					} else if (val == '.') {
						que.add(new int[] {i, j});
						visited[i][j] = true;
					} else if (Character.isUpperCase(val)) { //문
						if (key.contains(Character.toLowerCase(val))) { //열쇠가 있으면 열린다
							que.add(new int[] {i, j});
							visited[i][j] = true;
						} else { //없으면 안열린다
							doors[val - 'A'].add(new int[] {i, j});
						}
					} else if (Character.isLowerCase(val)) { //열쇠이면
						key.add(val);
						que.add(new int[] {i, j});
						visited[i][j] = true;
						for (int[] pos : doors[val - 'a']) {
							que.add(pos);
							visited[pos[0]][pos[1]] = true;
						}
					}
				}
			}
		}

		while (!que.isEmpty()) {
			int[] pos = que.poll();
			int x = pos[0];
			int y = pos[1];
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (isInRange(nx, ny, h, w) && !visited[nx][ny]) {
					char val = input[nx][ny];
					if (val == '$') {
						answer += 1;
						que.add(new int[] {nx, ny});
						visited[nx][ny] = true;
					} else if (val == '.') {
						que.add(new int[] {nx, ny});
						visited[nx][ny] = true;
					} else if (Character.isUpperCase(val)) {
						if (key.contains(Character.toLowerCase(val))) {
							que.add(new int[] {nx, ny});
							visited[nx][ny] = true;
						} else {
							doors[val - 'A'].add(new int[] {nx, ny});
						}
					} else if (Character.isLowerCase(val)) {
						key.add(val);
						que.add(new int[] {nx, ny});
						visited[nx][ny] = true;
						for (int[] p : doors[val - 'a']) {
							que.add(p);
							visited[p[0]][p[1]] = true;
						}
					}
				}
			}
		}
		return answer;
	}

	public static boolean isInRange(int nx, int ny, int h, int w) {
		return (nx >= 0 && ny >= 0 && nx < h && ny < w);
	}

}
