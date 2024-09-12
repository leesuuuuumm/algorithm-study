import java.io.*;
import java.util.*;

public class Main {

	static int R, C, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int ans = 0;

		if ((R * C) % 2 == 1 && K == 1 || (R * C) % 2 == 0)
			ans = 1;
		System.out.println(ans);

	}
}
