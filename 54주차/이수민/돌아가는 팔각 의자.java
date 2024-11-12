import java.io.*;
import java.util.*;
public class Main {
	static int[][] chair;
	static int k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		chair = new int[4][8];
		for (int i = 0; i < 4; i++) {
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < 8; j++) {
				chair[i][j] = ch[j] - '0';
			}
		}
		k = Integer.parseInt(br.readLine());

		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			selectRotationTable(num - 1, d);
		}
		System.out.println(attendSPeople());

	}

	private static int attendSPeople() {
		int answer = 0;
		for (int i = 0; i < 4; i++) {
			if (chair[i][0] == 1) {
				answer += (int) Math.pow(2, i);

			}
		}
		return answer;

	}

	private static void selectRotationTable(int num, int d) {
		int[] dir = new int[4];
		dir[num] = d;
		if (num == 0) {
			for (int i = num + 1; i < 4; i++) {
				if (chair[i - 1][2] != chair[i][6]) {
					dir[i] = dir[i - 1] * -1;
				} else
					break;
			}
		} else if (num == 1 || num == 2) {
			for (int i = num; i > 0; i--) {
				if (chair[i - 1][2] != chair[i][6]) {
					dir[i - 1] = dir[i] * -1;
				} else
					break;
			}
			for (int i = num; i < 3; i++) {
				if (chair[i][2] != chair[i + 1][6]) {
					dir[i + 1] = dir[i] * -1;
				} else
					break;
			}
		} else {
			for (int i = num; i > 0; i--) {
				if (chair[i - 1][2] != chair[i][6]) {
					dir[i - 1] = dir[i] * -1;
				} else
					break;
			}
		}
		rotateTable(dir);
	}

	private static void rotateTable(int[] dir) {
		for (int i = 0; i < dir.length; i++) {
			if (dir[i] == 1) {
				int tmp = chair[i][7];
				for (int j = 7; j >= 1; j--) {
					chair[i][j] = chair[i][j - 1];
				}
				chair[i][0] = tmp;

			} else if (dir[i] == -1) { 
				int tmp = chair[i][0];

				for (int j = 1; j < 8; j++) {
					chair[i][j - 1] = chair[i][j];
				}
				chair[i][7] = tmp;
			}

		}

	}
}
