package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_1343_폴리오미노 {
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		StringBuilder result = new StringBuilder();


		while (!input.isEmpty()) {
			if (input.startsWith("XXXX")) {
				result.append("AAAA");
				input = input.substring(4);
			} else if (input.startsWith("XX")) {
				result.append("BB");
				input = input.substring(2);
			} else {
				result.append(input.charAt(0));
				input = input.substring(1);
			}
		}

		System.out.println(result.toString().contains("X") ? -1 : result);
	}
}
