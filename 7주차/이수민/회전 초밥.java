import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int[] rotateRice = new int[N + k - 1];
		for (int i = 0; i < N; i++) {
			rotateRice[i] = Integer.parseInt(br.readLine());
		}

		for (int i = N; i < N + k - 1; i++) {
			rotateRice[i] = rotateRice[i - N];
		}

		int[] v = new int[d + 1];

		int p1 = 0;
		int p2 = k - 1;
		int count = 0;
		int max = 0;

		for (int i = 0; i < k; i++) {
			if (v[rotateRice[i]] == 0) {
				count++;
			}
			v[rotateRice[i]]++;
		}

		if (v[c] == 0) {
			max = Math.max(max, count + 1);
		}

		while (true) {
			if (v[rotateRice[p1]] == 1) {
				count--;
			}
			v[rotateRice[p1]] = v[rotateRice[p1]] - 1 < 0 ? 0 : --v[rotateRice[p1]];
			p1++;
			p2++;

			if (v[rotateRice[p2]] == 0) {
				count++;
			}
			v[rotateRice[p2]]++;

			if (v[c] == 0) {
				max = Math.max(max, count + 1);
			} else {
				max = Math.max(max, count);
			}
			if (p2 + 1 >= N + k - 1) {
				break;
			}

		}
		System.out.println(max);

	}
}
