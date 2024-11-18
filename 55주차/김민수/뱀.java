import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 뱀 {
	static int N;
	static boolean[][] snake;
	static boolean[][] apple;
	//오른쪽, 아래, 왼, 위
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static Queue<Snake> snakePos;
	static Queue<int[]> snakes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		apple = new boolean[N + 1][N + 1];
		snake = new boolean[N + 1][N + 1];

		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			apple[x][y] = true;

		}

		snakes = new ArrayDeque<>();
		snakePos = new ArrayDeque<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			snakePos.add(new Snake(x, dir));
		}

		boolean flag = true;
		int x = 1;
		int y = 1;
		int curDir = 0;
		int answer = 0;
		snake[1][1] = true;
		snakes.add(new int[] {1, 1});
		int time=0;
		while (true) {
			if(!snakePos.isEmpty()) {
				Snake s = snakePos.poll();
				char dir = s.dir;
				int count = s.count;

				for (int i = time; i < count; i++) {
					answer += 1;
					x += dx[curDir];
					y += dy[curDir];
					if (!isInRange(x, y) || snake[x][y]) {
						System.out.println(answer);
						return;
					} else {
						snake[x][y] = true;
						if (apple[x][y]) {
							apple[x][y] = false;
						} else {
							int[] tail = snakes.poll();
							snake[tail[0]][tail[1]] = false;
						}
						snakes.add(new int[] {x, y});
					}
				}

				if (dir == 'L') {
					curDir = (curDir + 3) % 4;
				} else {
					curDir = (curDir + 1) % 4;
				}
				time = answer;
			}else{
				while(true){
					answer += 1;
					x += dx[curDir];
					y += dy[curDir];
					if (!isInRange(x, y) || snake[x][y]) {
						System.out.println(answer);
						return;
					} else {
						snake[x][y] = true;
						if (apple[x][y]) {
							apple[x][y] = false;
						} else {
							int[] tail = snakes.poll();
							snake[tail[0]][tail[1]] = false;
						}
						snakes.add(new int[] {x, y});
					}
				}
			}
		}

	}

	public static class Snake {
		public int count;
		public char dir;

		public Snake(int count, char dir) {
			this.count = count;
			this.dir = dir;
		}
	}

	public static boolean isInRange(int nx, int ny) {
		return (nx >= 1 && ny >= 1 && nx <= N && ny <= N);
	}
}
