import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 뱀 {
	static int N;
	static boolean[][] snake, apple;
	static int[] dx = {0, 1, 0, -1}; // 방향: 오른쪽, 아래, 왼쪽, 위
	static int[] dy = {1, 0, -1, 0};
	static Queue<Direction> directionQueue;
	static Queue<int[]> snakeBody;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());

		// 초기화
		apple = new boolean[N + 1][N + 1];
		snake = new boolean[N + 1][N + 1];

		// 사과 위치 입력
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			apple[x][y] = true;
		}

		// 방향 전환 입력
		int L = Integer.parseInt(br.readLine());
		directionQueue = new ArrayDeque<>();
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			directionQueue.add(new Direction(time, dir));
		}

		System.out.println(simulateGame());
	}

	// 게임 시뮬레이션
	private static int simulateGame() {
		int x = 1, y = 1, curDir = 0, time = 0;
		snakeBody = new ArrayDeque<>();
		snakeBody.add(new int[]{x, y});
		snake[x][y] = true;

		while (true) {
			// 방향 전환 처리
			if (!directionQueue.isEmpty() && directionQueue.peek().time == time) {
				curDir = updateDirection(curDir, directionQueue.poll().dir);
			}

			// 다음 위치로 이동
			x += dx[curDir];
			y += dy[curDir];
			time++;

			// 종료 조건: 벽이나 자신의 몸에 부딪힌 경우
			if (!isInBounds(x, y) || snake[x][y]) return time;

			// 이동한 위치 처리
			snake[x][y] = true;
			snakeBody.add(new int[]{x, y});

			// 사과가 없다면 꼬리 제거
			if (!apple[x][y]) {
				int[] tail = snakeBody.poll();
				snake[tail[0]][tail[1]] = false;
			} else {
				apple[x][y] = false; // 사과 먹음
			}
		}
	}

	// 방향 전환
	private static int updateDirection(int curDir, char turn) {
		return turn == 'L' ? (curDir + 3) % 4 : (curDir + 1) % 4;
	}

	// 범위 체크
	private static boolean isInBounds(int x, int y) {
		return x >= 1 && y >= 1 && x <= N && y <= N;
	}

	// 방향 전환 정보를 담는 클래스
	private static class Direction {
		int time;
		char dir;

		public Direction(int time, char dir) {
			this.time = time;
			this.dir = dir;
		}
	}
}
