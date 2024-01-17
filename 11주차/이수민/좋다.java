import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int cnt = 0;

		for (int i = 0; i < N; i++) {
			int l = 0;
			int h = N - 1;

			while (true) {

				if (i == l)
					l++;
				else if (i == h)
					h--;
				if (l == h) {
					break;
				}
				if (arr[l] + arr[h] > arr[i]) {
					h--;
				} else if (arr[l] + arr[h] < arr[i]) {
					l++;
				} else if (arr[l] + arr[h] == arr[i]) {
					cnt++;
					break;
				}

			}

		}

		System.out.println(cnt);

	}
}
