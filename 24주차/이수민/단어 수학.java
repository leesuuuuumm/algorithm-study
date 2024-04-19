import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static ArrayList<Character> list;
	static int[] input;
	static boolean[] v;
	static String[] str;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		str = new String[N];
		list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			str[i] = br.readLine();
			char[] ch = str[i].toCharArray();

			for (int j = 0; j < ch.length; j++) {
				if (list.size() == 0 || !list.contains(ch[j])) {
					list.add(ch[j]);
				}
			}
		}

		v = new boolean[10];
		input = new int[list.size()];
		alp = new int[26];
		max = Integer.MIN_VALUE;
		nPr(0);
		System.out.println(max);
	}

	static int max;

	private static void nPr(int cnt) {
		if (cnt == list.size()) {
			max = Math.max(calculateSum(), max);

			return;

		}
		for (int i = 0; i <= 9; i++) {
			if (v[i])
				continue;

			input[cnt] = i;
			v[i] = true;
			nPr(cnt + 1);
			v[i] = false;

		}
	}

	static int[] alp;

	private static int calculateSum() {
		for (int j = 0; j < input.length; j++) {
			alp[list.get(j) - 65] = input[j];
		}
		int sum = 0;
		for (int i = 0; i < N; i++) {
			StringBuilder sb = new StringBuilder();

			char[] ch = str[i].toCharArray();

			for (int j = 0; j < ch.length; j++) {

				sb.append(alp[ch[j] - 65]);
			}
			sum += Integer.parseInt(sb.toString());

		}
		//System.out.println(Arrays.toString(alp));
		return sum;

	}
}
