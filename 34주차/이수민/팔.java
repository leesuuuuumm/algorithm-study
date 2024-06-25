import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String[] L = st.nextToken().split("");
		String[] R = st.nextToken().split("");

		int ans = 0;
		if (L.length == R.length) {
			for (int i = 0; i < L.length; i++) {
				if (L[i].equals("8") && R[i].equals("8"))
					ans++;
				else if (!L[i].equals(R[i]))
					break;

			}

		}
		System.out.println(ans);

	}
}
