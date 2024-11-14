package solve;

import java.util.*;
import java.io.*;

public class 세수의합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] map = new int[N];

		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(map);
		
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				set.add(map[i] + map[j]);
			}
		}
		int max = -1;
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				int target = map[j] - map[i];
				if (set.contains(target)) {
					max = Math.max(max, map[j]);
				}
			}
		}
		System.out.println(max);

	}

}
