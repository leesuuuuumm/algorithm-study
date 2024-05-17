import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] s = Integer.toBinaryString(N + 1).split("");
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i < s.length; i++) {
			if (s[i].equals("0"))
				sb.append(4);
			else
				sb.append(7);
		}
		System.out.println(sb);

	}
}
