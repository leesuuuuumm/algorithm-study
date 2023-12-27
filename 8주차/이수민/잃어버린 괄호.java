import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		String[] str = s.split("-");

		int ans = Integer.MAX_VALUE;

		for (int i = 0; i < str.length; i++) {

			String[] plus = str[i].split("\\+");
			int sum = 0;
			for (int j = 0; j < plus.length; j++) {
				sum += Integer.parseInt(plus[j]);
			}

			if (ans == Integer.MAX_VALUE) {
				ans = sum;
			} else {
				ans -= sum;
			}
		}
		System.out.println(ans);
	}
}
