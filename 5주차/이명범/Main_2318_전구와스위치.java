package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_2318_전구와스위치 {

	static int N;
	static char[] origin;
	static char[] expect;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		origin = br.readLine().toCharArray();
		expect = br.readLine().toCharArray();

		boolean[] switches = convert(origin);
		int a = process(switches);
		switches = convert(origin);
		switches[0] = !switches[0];
		switches[1] = !switches[1];
		int b = process(switches);

		if (a == INF && b == INF)
			System.out.println(-1);
		else
			System.out.println(Math.min(a, b + 1));
	}

	static final int INF = 100_001;

	private static int process(boolean[] switches) {
		int sum = 0;
		for (int i = 1; i < N; i++) {
			if (expect[i - 1] == (switches[i - 1] ? '1' : '0'))
				continue;

			sum++;
			switches[i - 1] = !switches[i - 1];
			switches[i] = !switches[i];
			if (i + 1 >= N)
				continue;
			switches[i + 1] = !switches[i + 1];
		}

		if (check(switches))
			return sum;

		return INF;
	}

	private static boolean check(boolean[] switches) {
		for (int i = 0; i < N; i++) {
			if (expect[i] != (switches[i] ? '1' : '0'))
				return false;
		}
		return true;
	}

	private static boolean[] convert(char[] arr) {
		boolean[] result = new boolean[N];
		for (int i = 0; i < N; i++) {
			result[i] = arr[i] != '0';
		}
		return result;
	}
}
