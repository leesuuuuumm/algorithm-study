import java.io.*;
import java.util.*;

public class Main {

	static int R;
	static int[] tmp = { 0, 1, 2 };
	static int[] selc;
	static int ans;

	public static void main(String[] args) throws IOException {
		// 3의배수인 겨ㅓㅇ우는 각자리수의 합이3임
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		R = Integer.parseInt(br.readLine());

		selc = new int[R];
		ans = 0;

		duNPR(0);
		System.out.println(ans);

	}

	private static void duNPR(int cnt) {
		if (cnt == R) {
			if (selc[0] == 0)
				return;

			int sum = 0;
			for (int i = 0; i < R; i++) {
				sum += selc[i];
			}

			if (sum % 3 == 0)
				ans++;
			return;
		}
		for (int i = 0; i < 3; i++) {
			selc[cnt] = i;
			duNPR(cnt + 1);
		}

	}
}
