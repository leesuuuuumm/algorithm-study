package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main_1553_도미노찾기 {

	static int[][] nums;
	static boolean[][] visit;
	static int[][] keys;
	static int result;
	static Map<Integer, List<int[][]>> map;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		nums = new int[8][7];
		visit = new boolean[8][7];
		keys = new int[7][7];
		map = new HashMap<>();
		for (int i = 0; i < 8; i++) {
			String s = br.readLine();
			for (int j = 0; j < 7; j++) {
				nums[i][j] = s.charAt(j) - '0';
			}
		}
		int count = 1;
		for (int i = 0; i < 7; i++) {
			for (int j = i; j < 7; j++) {
				keys[i][j] = count;
				keys[j][i] = count;
				count++;
			}
		}
		int[] dr = {1, 0};
		int[] dc = {0, 1};
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				int v1 = nums[i][j];
				for (int k = 0; k < 2; k++) {
					int oi = i + dr[k];
					int oj = j + dc[k];

					if (oi >= 8 || oj >= 7)
						continue;
					int v2 = nums[oi][oj];

					int key = keys[v1][v2];

					List<int[][]> value = map.getOrDefault(key, new ArrayList<>());
					value.add(new int[][] {{i, j}, {oi, oj}});
					map.put(key, value);
				}
			}
		}
		recur(1);
		System.out.println(result);
	}

	private static void recur(int count) {
		if (count == 29) {
			result++;
			return;
		}

		List<int[][]> points = map.getOrDefault(count, Collections.emptyList());

		for (int[][] point : points) {
			int[] p1 = point[0];
			int[] p2 = point[1];

			if (visit[p1[0]][p1[1]] || visit[p2[0]][p2[1]])
				continue;

			visit[p1[0]][p1[1]] = true;
			visit[p2[0]][p2[1]] = true;
			recur(count + 1);
			visit[p1[0]][p1[1]] = false;
			visit[p2[0]][p2[1]] = false;
		}
	}
}
