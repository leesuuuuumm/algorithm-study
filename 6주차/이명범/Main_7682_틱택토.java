package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main_7682_틱택토 {

	static final int SIZE = 9;
	static Set<Integer> set = new HashSet<>();
	static List<Integer> terminatedFlags = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		init();
		recur(0, 0);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder();
		String input;
		while (!(input = br.readLine()).equals("end")) {
			int state = 0;
			for (int i = 0; i < SIZE; i++) {
				char ch = input.charAt(i);

				if (ch == 'X')
					state = state | 1 << i;
				if (ch == 'O')
					state = state | 1 << (i + SIZE);
			}
			result.append(set.contains(state) ? "valid" : "invalid").append("\n");
		}
		System.out.println(result);
	}

	private static void init() {
		for (int r = 0; r < 3; r++) {
			int temp = 0;
			for (int c = 0; c < 3; c++) {
				temp = temp | (1 << r * 3 + c);
			}
			terminatedFlags.add(temp);
		}
		for (int c = 0; c < 3; c++) {
			int temp = 0;
			for (int r = 0; r < 3; r++) {
				temp = temp | (1 << r * 3 + c);
			}
			terminatedFlags.add(temp);
		}
		int temp1 = 0;
		int temp2 = 0;
		for (int r = 0; r < 3; r++) {
			temp1 = temp1 | (1 << r * 3 + r);
			temp2 = temp2 | (1 << r * 3 + (2 - r));
		}
		terminatedFlags.add(temp1);
		terminatedFlags.add(temp2);
	}

	private static void recur(int depth, int flag) {
		if (isTerminated(flag)) {
			set.add(flag);
			return;
		}

		if (depth == SIZE) {
			set.add(flag);
			return;
		}

		for (int i = 0; i < SIZE; i++) {
			if ((flag & 1 << i) != 0 || (flag & 1 << (i + SIZE)) != 0)
				continue;

			recur(depth + 1, depth % 2 == 0 ? flag | 1 << i : flag | 1 << (i + SIZE));
		}
	}

	private static boolean isTerminated(int flag) {
		int x = flag & ((1 << SIZE) - 1);
		int o = flag >> SIZE;

		for (Integer terminatedFlag : terminatedFlags) {
			if ((x & terminatedFlag) == terminatedFlag || (o & terminatedFlag) == terminatedFlag)
				return true;
		}
		return false;
	}
}
