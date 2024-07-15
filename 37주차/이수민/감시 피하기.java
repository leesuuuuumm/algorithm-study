import java.io.*;
import java.util.*;

public class Main {

	static class Point { 
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static String[][] classroom;
	static int N;
	static boolean everyFlag;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		classroom = new String[N][N];
		for (int i = 0; i < N; i++) {
			classroom[i] = br.readLine().split(" ");
		}
		sb = new StringBuilder();
		tmp = new Point[3];
		everyFlag = false;
		getInfo();
		installContainer(0, 0);

		if (sb.length() == 0) {
			System.out.println("NO");
		} else {
			System.out.println(sb.toString());
		}

	}

	static Point[] tmp;

	private static void installContainer(int cnt, int start) {
		if (everyFlag) {
			return;
		}
		if (cnt == 3) {
			for (int i = 0; i < tmp.length; i++) {
				int nr = tmp[i].r;
				int nc = tmp[i].c;
				classroom[nr][nc] = "O";
			}

			avoidTeacher();
			for (int i = 0; i < tmp.length; i++) {
				int nr = tmp[i].r;
				int nc = tmp[i].c;

				classroom[nr][nc] = "X";
			}

			return;
		}

		for (int i = start; i < emp.size(); i++) {
			tmp[cnt] = emp.get(i);
			installContainer(cnt + 1, i + 1);
		}

	}

	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	private static void avoidTeacher() {
		for (int i = 0; i < teacher.size(); i++) {
			Point cur = teacher.get(i);
			
			for (int d = 0; d < 4; d++) {
				int nr = cur.r;
				int nc = cur.c;

				while (true) {
					nr += dr[d];
					nc += dc[d];

					if (!check(nr, nc) || classroom[nr][nc].equals("O")) {
						break;
					} else if (classroom[nr][nc].equals("S")) {
						return;
					}

				}

			}
		}
		everyFlag = true;
		sb.append("YES");

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

	static List<Point> emp;
	static List<Point> teacher;

	private static void getInfo() {
		emp = new ArrayList<>();
		teacher = new ArrayList<>();

		for (int i = 0; i < classroom.length; i++) {
			for (int j = 0; j < classroom.length; j++) {
				if (classroom[i][j].equals("X")) {
					emp.add(new Point(i, j));
				} else if (classroom[i][j].equals("T")) {
					teacher.add(new Point(i, j));
				}
			}
		}
	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(classroom[i][j] + " ");
			}
			System.out.println();
		}

	}
}
