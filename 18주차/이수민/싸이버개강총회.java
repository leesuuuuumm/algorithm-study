import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");

		int[] time = new int[3]; // 개총 시작, 개총 끝, 스트리밍 끝

		for (int i = 0; i < 3; i++) {
			String[] s = str[i].split(":");
			time[i] = (Integer.parseInt(s[0]) * 60) + Integer.parseInt(s[1]);
		}

		HashSet<String> set = new HashSet<>();
		String input = null;
		int answer = 0;

		while ((input = br.readLine()) != null) {

			String[] user = input.split(" ");

			String[] t = user[0].split(":");

			int chatTime = (Integer.parseInt(t[0]) * 60) + Integer.parseInt(t[1]);

			if (time[0] >= chatTime) {
				set.add(user[1]);
			} else if (time[1] <= chatTime && time[2] >= chatTime && set.contains(user[1])) {
				set.remove(user[1]);
				answer++;
			}
		}

		System.out.println(answer);
	}
}
