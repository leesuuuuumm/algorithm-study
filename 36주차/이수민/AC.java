import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		c: for (int t = 0; t < T; t++) {
			String[] str = br.readLine().split("");
			int N = Integer.parseInt(br.readLine());
			Deque<String> dq = new ArrayDeque<>();
			String s = br.readLine();

			String[] num = s.subSequence(1, s.length() - 1).toString().split(",");

			for (int i = 0; i < num.length; i++) {
				if (num[i].equals(""))
					continue;
				dq.offer(num[i]);
			}

			int idx = 1;// 1: 앞, -1: 뒤

			for (int i = 0; i < str.length; i++) {
				if (str[i].equals("D") && dq.isEmpty()) {
					sb.append("error").append("\n");
					continue c;
				}

				if (str[i].equals("R")) {
					idx *= -1;
				} else if (str[i].equals("D")) {
					if (idx == 1) {
						dq.pollFirst();
					} else {
						dq.pollLast();
					}
				}
			}

			sb.append("[");
			if (idx == 1) {

				while (!dq.isEmpty()) {
					sb.append(dq.pollFirst());
					if (dq.size() > 0) {
						sb.append(",");
					}
				}
			} else {
				while (!dq.isEmpty()) {
					sb.append(dq.pollLast());
					if (dq.size() > 0) {
						sb.append(",");
					}
				}
			}
			sb.append("]").append("\n");
		}
		System.out.println(sb.toString().trim());

	}

}
