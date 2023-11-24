import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int max = 0;

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}

		int s = 0;
		int e = 0;
		boolean[] v = new boolean[max + 1];
		long ans = arr.length;
		v[arr[0]] = true;

		while (e < arr.length) {
			if (s == e && e < arr.length - 1 && v[arr[e + 1]]) {
				s++;
				e++;
			} else if (e < arr.length - 1 && !v[arr[e + 1]]) {
				e++;
				v[arr[e]] = true;
			} else if (e == arr.length - 1) {
				if (s == e) {
					break;
				} else {
					v[arr[s]] = false;
					ans += (e - s);
					s++;
				}
			} else if (v[arr[e + 1]]) {
				v[arr[s]] = false;
				ans += (e - s);
				s++;
			}
		}
		System.out.println(ans);

	}

}
