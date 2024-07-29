import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str = br.readLine().toCharArray();

		int s = 0;
		int i = 0;

		k: for (i = 0; i < str.length; i++) {
			int e = str.length - 1;
			s = i;

			while (true) {
				if (s >= e) {
					break k;
				}
				if (str[s] != str[e]) {
					break;
				}

				s++;
				e--;
			}
		}
		if (i == 0) {
			System.out.println(str.length);
		} else {
			System.out.println(str.length + (i));

		}


	}
}
