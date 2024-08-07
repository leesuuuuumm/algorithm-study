import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		String[] S = new String[N];
		for (int i = 0; i < N; i++) {
			S[i] = br.readLine();
		}

		Arrays.sort(S);

		int ans = 0;
		for (int i = 0; i < M; i++) {
			String f = br.readLine();

			int L = 0;
			int R = S.length - 1;

			while (L <= R) {
				int mid = (L + R) / 2;

				if (S[mid].startsWith(f)) {
					ans++;
					break;
				}

				if (S[mid].compareTo(f) > 0) { // 자기가 더 큰거
					R = mid - 1;
				} else if (S[mid].compareTo(f) < 0) {
					L = mid + 1;
				}
			}
		}
		System.out.println(ans);

	}

}
