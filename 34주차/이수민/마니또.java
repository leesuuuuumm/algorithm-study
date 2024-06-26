import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int t = 1;; t++) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			String[][] name = new String[N][2];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				name[i][0] = st.nextToken();
				name[i][1] = st.nextToken();
			}

			boolean[] v = new boolean[N];
			int ans = 0;

			for (int i = 0; i < N; i++) {
				if (v[i])
					continue;
				v[i] = true;

				String s1 = name[i][1];
				boolean f = false;
				a: while (true) {
					for (int j = 0; j < N; j++) {
						if (!v[j] && s1.equals(name[j][0])) {
							v[j] = true;
							s1 = name[j][1];
							f = true;
							continue a;
						}
					}
					if (f) {
						ans++;
					}
					break;
				}
			}

			sb.append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);

	}
}
