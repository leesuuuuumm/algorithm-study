import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class User {
		int no;
		int r;
		int c;
		int dir;
		int power;
		int weapon;
		int point;

		public User(int no, int r, int c, int dir, int power) {
			this.no = no;
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.power = power;
			this.weapon = 0;
			this.point = 0;
		}

		public void process() {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			if (isArrayOutOfBounds(nr, nc)) {
				changeDirection(2);
				nr = r + dr[dir];
				nc = c + dc[dir];
			}
			r = nr;
			c = nc;

			User otherUser = findOtherUser(no, r, c);
			if (otherUser != null) {
				fight(otherUser);
			} else if (gunExists(this.r, this.c))
				getGun();
		}

		private void fight(User o) {
			if (canWin(o)) {
				this.point += this.getTotalPower() - o.getTotalPower();
				o.lose();
				if (gunExists(this.r, this.c))
					getGun();
			} else {
				o.point += o.getTotalPower() - this.getTotalPower();
				this.lose();
				if (gunExists(o.r, o.c))
					o.getGun();
			}
		}

		private void lose() {
			this.putGun();

			int nr = this.r + dr[dir];
			int nc = this.c + dc[dir];
			while (isArrayOutOfBounds(nr, nc) || findOtherUser(this.no, nr, nc) != null) {
				changeDirection(1);
				nr = this.r + dr[dir];
				nc = this.c + dc[dir];
			}
			this.r = nr;
			this.c = nc;

			if (gunExists(this.r, this.c))
				getGun();
		}

		private void putGun() {
			grid[r][c].add(this.weapon);
			this.weapon = 0;
		}

		private boolean canWin(User o) {
			return this.getTotalPower() > o.getTotalPower() ||
				(this.getTotalPower() == o.getTotalPower() && this.power > o.power);
		}

		private int getTotalPower() {
			return this.power + this.weapon;
		}

		private User findOtherUser(int no, int r, int c) {
			for (int i = 1; i <= M; i++) {
				if (i == no)
					continue;

				if (users[i].r == r && users[i].c == c)
					return users[i];
			}
			return null;
		}

		private void getGun() {
			Integer get = grid[r][c].poll();
			if (this.weapon == 0) {
				this.weapon = get;
			} else {
				grid[r][c].add(Math.min(this.weapon, get));
				this.weapon = Math.max(this.weapon, get);
			}
		}

		private void changeDirection(int count) {
			this.dir = (this.dir + count) % 4;
		}
	}

	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	static int N, M, K;
	static PriorityQueue<Integer>[][] grid;
	static User[] users;

	public static void main(String[] args) throws Exception {
		input();
		for (int i = 0; i < K; i++) {
			for (int no = 1; no <= M; no++) {
				users[no].process();
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int no = 1; no <= M; no++) {
			sb.append(users[no].point).append(" ");
		}
		System.out.print(sb);
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		grid = new PriorityQueue[N + 1][N + 1];
		users = new User[M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				grid[i][j] = new PriorityQueue<>(Comparator.reverseOrder());
				grid[i][j].add(Integer.parseInt(st.nextToken()));
			}
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int power = Integer.parseInt(st.nextToken());

			users[i] = new User(i, r, c, dir, power);
		}
	}

	private static boolean isArrayOutOfBounds(int r, int c) {
		return r <= 0 || r > N || c <= 0 || c > N;
	}

	private static boolean gunExists(int r, int c) {
		return !grid[r][c].isEmpty();
	}
}
