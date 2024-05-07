import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int[] t = new int[N / 2];
		int[] b = new int[N / 2];

		int k1 = 0;
		int k2 = 0;
		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) {
				b[k1++] = Integer.parseInt(br.readLine());
			} else {
				t[k2++] = H - Integer.parseInt(br.readLine()) + 1;
			}
		}
		Arrays.sort(t);
		Arrays.sort(b);
		int[] bottom = new int[H + 1];
		int[] top = new int[H + 1];
		int cnt = 1;
		int cunt = 1;
		for (int i = 0; i < t.length; i++) {
			if (i == b.length - 1 || b[i] != b[i + 1]) {
				bottom[b[i]] = cnt;
				cnt = 1;
			} else {
				cnt++;
			}

			if (i == t.length - 1 || t[i] != t[i + 1]) {
				top[t[i]] = cunt;
				cunt = 1;
			} else {
				cunt++;
			}
		}

		for (int i = bottom.length - 1; i >= 2; i--) {
			bottom[i - 1] += bottom[i];
		}
		for (int i = 1; i < top.length - 1; i++) {
			top[i + 1] += top[i];
		}
		int min = Integer.MAX_VALUE;
		cnt = 1;
		for (int i = 1; i < H + 1; i++) {
			if (min >= top[i] + bottom[i]) {
				if (min == top[i] + bottom[i]) {
					cnt++;
				} else {
					cnt = 1;
				}
				min = top[i] + bottom[i];
			}
		}
		System.out.println(min + " " + cnt);

	}

}
