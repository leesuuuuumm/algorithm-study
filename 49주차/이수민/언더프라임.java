import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		boolean[] v = new boolean[B + 1];

		for (int i = 2; i <= B; i++) {
			if (v[i])
				continue;

			for (int j = i + i; j <= B; j += i) {
				if (v[j])
					continue;
				v[j] = true;
			}
		}
		int ans = 0;

		for (int i = A; i <= B; i++) {
			if (!v[i])
				continue;
			int cnt = 1;
			int num = i;
			for (int j = 2; j <= Math.sqrt(i); j++) {
				while (num / j != 1 && num % j == 0) {
					num /= j;
					cnt++;
				}
			}
			if (!v[cnt])
				ans++;
		}
		System.out.println(ans);
	}
}
