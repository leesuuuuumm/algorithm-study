// [BOJ] AC

import java.util.*;

class Main {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		Queue<Character> q = new LinkedList<Character>(); //
		List<String> inputList = new ArrayList<String>();
		int t = sc.nextInt();
		for (int i = 0; i < t; i++) {
			boolean reverse = false;
			boolean error = false;
			q.clear();
			inputList.clear();
			for (char c : sc.next().toCharArray()) {
				q.add(c);
			}
			int length = sc.nextInt();
			String input = sc.next();
			if (length != 0) {
				String[] inputArr = input.substring(1, input.length() - 1).split(",");
				for (int j = 0; j < inputArr.length; j++) {
					inputList.add(inputArr[j]);
				}
			}
			while (q.size() != 0 && !error) {
				switch (q.poll()) {
				case 'R':
					reverse = !reverse;
					break;
				case 'D':
					if (inputList.size() == 0) {
						sb.append("error\n");
						error = true;
						break;
					}
					if (reverse) {
						inputList.remove(inputList.size() - 1);
						break;
					} else {
						inputList.remove(0);
						break;
					}
				default:
					break;
				}
			}
			int cursor = 0;
			if (!error && !reverse) {
				sb.append("[");
				for (String s : inputList) {
					sb.append(s);
					if (++cursor < inputList.size()) {
						sb.append(",");
					}
				}
				sb.append("]\n");
			} else if (!error && reverse) {
				Collections.reverse(inputList);
				sb.append("[");
				for (String s : inputList) {
					sb.append(s);
					if (++cursor < inputList.size()) {
						sb.append(",");
					}
				}
				sb.append("]\n");
			}
		}
		System.out.println(sb);
	}
}