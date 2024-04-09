import java.io.*;
import java.util.*;

public class Main {

	static int[] dice; // 주사위에서 나올 수 10개
	static int[] num; // 중복순열 (말의 순서 뽑기)
	static int[] info; // 말의 위치가 들어있는 객체 배열

	static boolean[] v;
	static int max;

	static int[] map = { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0,
			// 0~21

			10, 13, 16, 19, 25, 30, 35, 40, 0, // 22~30
			20, 22, 24, 25, 30, 35, 40, 0, // 31 ~ 38
			30, 28, 27, 26, 25, 30, 35, 40, 0 }; // 39 ~ 47

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		dice = new int[10];
		num = new int[10];
		max = Integer.MIN_VALUE;

		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}

		permutation(0);
		System.out.println(max);

	}

	static boolean flag = true;

	private static void permutation(int cnt) {
		if (cnt == 10) {
			playGame();
			return;

		}

		for (int i = 1; i <= 4; i++) {
			num[cnt] = i;

			permutation(cnt + 1);

		}
	}

	private static void playGame() {
		int answer = 0;
		info = new int[5]; 
		v = new boolean[map.length]; 
		a: for (int i = 0; i < 10; i++) {
			if (info[num[i]] == -1)
				return;
			
			
			int r = info[num[i]];

			int nr = r;
			for (int j = 0; j < dice[i]; j++) {
				nr++;
				if (map[nr] == 0) {
					setVisited(r, false);
					info[num[i]] = -1;
					continue a;
				}
			}

			nr = check(nr);

			if (v[nr]) 
				return; 
			setVisited(r, false);
			setVisited(nr, true);

			answer += map[nr];
			info[num[i]] = nr;
			//print();
		}

		max = Math.max(answer, max);

	}

	private static void setVisited(int idx, boolean check) {
		if (idx == 20 || idx == 29 || idx == 37 || idx == 46) { // map[nr] = 40인 경우
			v[20] = check;
			v[29] = check;
			v[37] = check;
			v[46] = check;
		} else if (idx == 26 || idx == 34 || idx == 43) {
			v[26] = check;
			v[34] = check;
			v[43] = check;
		} else if (idx == 27 || idx == 35 || idx == 44) {
			v[27] = check;
			v[35] = check;
			v[44] = check;
		} else if (idx == 28 || idx == 36 || idx == 45) {
			v[28] = check;
			v[36] = check;
			v[45] = check;
		} else {
			v[idx] = check;
		}

	}

	private static int check(int nr) {
		if (nr == 5) {
			nr = 22;

		} else if (nr == 10) {
			nr = 31;
		} else if (nr == 15) {
			nr = 39;
		}

		return nr;

	}

	private static void print() {
		for (int r = 0; r < map.length; r++) {
			System.out.print(v[r] ? "t " : "f ");
		}
		System.out.println();
	}
}
