import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] str = new String[N];
		for (int i = 0; i < N; i++) {
			str[i] = br.readLine();
		}

		List<String> list = new ArrayList<>();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < str.length; i++) {

			if (str[i].contains(" ")) {
				String[] word = str[i].split(" ");
				boolean flag = false;
				for (int j = 0; j < word.length; j++) {
					if (!flag && (list.size() == 0 || !list.contains(word[j].substring(0, 1).toLowerCase()))) {
						flag = true;
					}
				}
				if (flag) {
					boolean check = false;
					for (int j = 0; j < word.length; j++) {
						if (!check && (list.size() == 0 || !list.contains(word[j].substring(0, 1).toLowerCase()))) {
							sb.append("[").append(word[j].substring(0, 1)).append("]")
									.append(word[j].substring(1, word[j].length())).append(" ");
							list.add(word[j].substring(0, 1).toLowerCase());
							check = true;
						} else if (flag || list.contains(word[j].substring(0, 1).toLowerCase())) {
							sb.append(word[j]).append(" ");

						}
					}
				} else if (!flag) {

					word = str[i].split("");
					for (int j = 0; j < word.length; j++) {
						if (word[j].equals(" ")) {
							sb.append(" ");
							continue;
						}
						if (!flag && (list.size() == 0 || !list.contains(word[j].toLowerCase()))) {
							sb.append("[").append(word[j]).append("]");
							list.add(word[j].toLowerCase());
							flag = true;
						} else if (flag || list.contains(word[j].toLowerCase())) {
							sb.append(word[j]);
						}
					}

				}

			} else {
				String[] word = str[i].split("");
				boolean flag = false;
				for (int j = 0; j < word.length; j++) {
					if (word[j].equals(" ")) {
						sb.append(" ");
						continue;
					}
					if (!flag && (list.size() == 0 || !list.contains(word[j].toLowerCase()))) {
						sb.append("[").append(word[j]).append("]");
						list.add(word[j].toLowerCase());
						flag = true;
					} else if (flag || list.contains(word[j].toLowerCase())) {

						sb.append(word[j]);
					}
				}

			}
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}
}
