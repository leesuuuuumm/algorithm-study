import java.util.*;
import java.io.*;

public class Main {

	 static int N, calNum;
	 static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	 static char[] permut, ch;
	 static boolean[] v;
	 static int[] num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		num = new int[N];
		int[] op = new int[4];
		permut = new char[N - 1];
		v = new boolean[N - 1];
		ch = new char[N - 1];
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < op.length; i++) {
			op[i] = Integer.parseInt(st.nextToken());
			for (int j = 0; j < op[i]; j++) {
				sb.append(i);
			}
		}

		for (int i = 0; i < ch.length; i++) {
			ch[i] = sb.charAt(i);
		}

		nPr(0);
		System.out.println(max);
		System.out.println(min);

	}

	private static void nPr(int cnt) {
		if (cnt == N - 1) {
			calNum = num[0];
			for (int i = 0; i < permut.length; i++) {
				//System.out.print(permut[i] + " ");
				switch (permut[i]) {
				case '0':
					calNum += num[i + 1];
					break;
				case '1':
					calNum -= num[i + 1];
					break;
				case '2':
					calNum *= num[i + 1];
					break;
				case '3':
					calNum /= num[i + 1];
					break;
				}

			}

			max = Math.max(calNum, max);
			min = Math.min(calNum, min);
			return;

		}
		for (int i = 0; i < N - 1; i++) {
			if (v[i])
				continue;

			permut[cnt] = ch[i];
			v[i] = true;

			nPr(cnt + 1);
			v[i] = false;

		}
	}
}
