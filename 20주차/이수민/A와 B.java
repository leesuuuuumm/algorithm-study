import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder S = new StringBuilder();
		StringBuilder T = new StringBuilder();

		S.append(br.readLine());
		T.append(br.readLine());

		while (true) {
			if (S.length() == T.length()) {
				if (S.toString().equals(T.toString())) {
					System.out.println(1);
				} else {
					System.out.println(0);
				}
				break;
			}

			// 맨뒤의 알파벳이 A면 제거
			// 맨뒤의 알파벳이 B면 제거후 뒤집기 !

			if (T.substring(T.length() - 1).equals("A")) {
				T.delete(T.length() - 1, T.length());
			} else {
				T.delete(T.length() - 1, T.length());
				T.reverse();
			}
		}

	}
}
