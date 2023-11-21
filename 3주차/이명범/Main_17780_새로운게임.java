package boj;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_17780_새로운게임 {

	static class Piece {
		int no;
		int dir;

		public Piece(int no, int dir) {
			this.no = no;
			this.dir = dir;
		}
	}

	static class Board {
		int r;
		int c;
		Piece top;
		Piece bottom;
		int color;
		int count;

		public Board(int r, int c, int color) {
			this.r = r;
			this.c = c;
			this.color = color;
			this.count = 0;
		}

		public void add(Piece piece) {
			if (bottom == null) {
				bottom = piece;
				return;
			}
			top = piece;
		}
	}

	static final int MAX_TURN = 1000;
	static int N, K;
	static Board[][] boards;
	static int turn = 1;

	static int[] dr = {0, 0, 0, -1, 1};
	static int[] dc = {0, 1, -1, 0, 0};

	public static void main(String[] args) throws Exception {
		input();
		while (turn <= MAX_TURN) {
			PriorityQueue<Board> sequenceOfProcess = new PriorityQueue<>(Comparator.comparing(o -> o.bottom.no));
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					if (boards[r][c].bottom == null)
						continue;

					sequenceOfProcess.add(boards[r][c]);
				}
			}

			while (!sequenceOfProcess.isEmpty()) {
				Board board = sequenceOfProcess.poll();

				int nr = board.r + dr[board.bottom.dir];
				int nc = board.c + dc[board.bottom.dir];
				int curBottomNo = board.bottom.no;

				if (isArrayOutOfBounds(nr, nc) || boards[nr][nc].color == 2) {
					board.bottom.dir = oppositeDir(board.bottom.dir);
					nr = board.r + dr[board.bottom.dir];
					nc = board.c + dc[board.bottom.dir];
				}

				if (isArrayOutOfBounds(nr, nc) || boards[nr][nc].color == 2)
					continue;

				boolean flag = boards[nr][nc].bottom == null;
				move(board, nr, nc);
				flag &= boards[nr][nc].bottom != null;

				if (flag && curBottomNo < boards[nr][nc].bottom.no)
					sequenceOfProcess.add(boards[nr][nc]);

				if (boards[nr][nc].count >= 4) {
					System.out.println(turn);
					return;
				}
			}
			turn++;
		}
		System.out.println(-1);
	}

	private static int oppositeDir(int dir) {
		switch (dir) {
			case 1:
				return 2;
			case 2:
				return 1;
			case 3:
				return 4;
			case 4:
				return 3;
		}
		return -1;
	}

	private static void move(Board board, int nr, int nc) {
		Piece top = board.top;
		Piece bottom = board.bottom;

		if (boards[nr][nc].color == 0) {
			boards[nr][nc].add(bottom);
			if (top != null)
				boards[nr][nc].add(top);
			boards[nr][nc].count += board.count;

			board.top = null;
			board.bottom = null;
			board.count = 0;
		}

		if (boards[nr][nc].color == 1) {
			if (top != null)
				boards[nr][nc].add(top);
			boards[nr][nc].add(bottom);
			boards[nr][nc].count += board.count;

			board.top = null;
			board.bottom = null;
			board.count = 0;
		}
	}

	private static boolean isArrayOutOfBounds(int r, int c) {
		return r < 1 || r > N || c < 1 || c > N;
	}

	private static void input() throws Exception {
		N = read();
		K = read();
		boards = new Board[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				int color = read();
				boards[r][c] = new Board(r, c, color);
			}
		}
		for (int i = 1; i <= K; i++) {
			int r = read();
			int c = read();
			int dir = read();
			boards[r][c].add(new Piece(i, dir));
			boards[r][c].count++;
		}
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
