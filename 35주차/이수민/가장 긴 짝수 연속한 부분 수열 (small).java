import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		int[] arr = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int p1 = 0;
		int p2 = 0;
		int h = 0;
		int z = 0;
		int ans = 0;

		while (p2 < N) {
			if (arr[p2] % 2 != 0) {
				h++;
			} else {
				z++;
			}

			ans = Math.max(ans, z);
			if (h > K) {
				if (arr[p1] % 2 != 0) {
					h--;
				} else {
					z--;
				}
				p1++;
			}
			p2++;
		}
		System.out.println(ans);

	}
}
