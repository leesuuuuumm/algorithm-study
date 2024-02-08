package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_5875_오타 {
	static int N, result;
	static char[] input;
	static int[] values;
	public static void main(String[] args) throws Exception {
		input();
		if (Math.abs(values[N]) != 2) {
			System.out.println(0);
			return;
		}
		for (int i = 1; i <= N; i++) {
			if (values[i] >= -2)
				continue;
			System.out.println(0);
			return;
		}
		if (values[N] == 2) {
			for (int i = 1; i <= N; i++) {
				if (values[i] >= 0)
					continue;
				System.out.println(0);
				return;
			}
			for (int i = N; i >= 1; i--) {
				if (values[i] <= 1)
					break;
				if (input[i] == ')')
					continue;
				result++;
			}
		} else {
			for (int i = 1; i <= N; i++) {
				if (input[i] == '(')
					continue;
				result++;
				if (values[i] <= -1)
					break;
			}
		}
		System.out.println(result);
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().toCharArray();
		N = input.length;
		values = new int[N + 1];
		char[] temp = new char[N + 1];
		for (int i = 0; i < N; i++) {
			temp[i + 1] = input[i];
		}
		input = temp;
		for (int i = 1; i <= N; i++) {
			values[i] += values[i - 1] + (input[i] == '(' ? 1 : -1);
		}
	}
}
