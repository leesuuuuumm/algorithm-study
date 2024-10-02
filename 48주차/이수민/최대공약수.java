import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());

		while (b != 0) {
			long tmp = b;
			b = a % b;
			a = tmp;
		}

		StringBuilder sb = new StringBuilder();
		for (long i = 0; i < a; i++) {
			sb.append(1);
		}
		System.out.println(sb);
	}
}
