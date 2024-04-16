import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine());

		long s = 1;
		long e = 2;
		StringBuilder sb = new StringBuilder();
		while (true) {
			long n = (long) Math.pow(e, 2) - (long) Math.pow(s, 2);
			if (n == G) {
				sb.append(e).append("\n");
				e++;
				s++;
			} else if (n < G || s == e) {
				e++;
			} else if (n > G) {
				s++;
			}

			if (e >= G)
				break;
		}
		if (sb.length() == 0) {
			sb.append(-1);
		}
		System.out.println(sb);

	}
}
