package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_1058_친구 {

	static int N;
	static boolean[][] friends;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		friends = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				if (str.charAt(j) == 'Y')
					friends[i][j] = true;
			}
		}
		boolean[][] temp = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (j == k)
						continue;
					if (friends[j][i] && friends[i][k])
						temp[j][k] = true;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				friends[i][j] = friends[i][j] | temp[i][j];
			}
		}
		int max = 0;
		for (int i = 0; i < N; i++) {
			int count = 0;
			for (int j = 0; j < N; j++) {
				if (friends[i][j])
					count++;
			}
			max = Math.max(max, count);
		}
		System.out.println(max);
	}
}
