package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_4195_친구네트워크 {

	static class User {
		String name;
		int group;

		public User(String name, int group) {
			this.name = name;
			this.group = group;
		}
	}

	static final int INF = 100_000;
	static int T, F;
	static Map<String, User> users;
	static int[] groups;
	static int[] counts;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			F = Integer.parseInt(br.readLine());
			users = new HashMap<>();
			groups = new int[2 * INF];
			counts = new int[2 * INF];
			for (int i = 0; i < 2 * INF; i++) {
				groups[i] = i;
				counts[i] = 1;
			}
			int index = 0;
			for (int i = 0; i < F; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());

				String idOfUserA = st.nextToken();
				String idOfUserB = st.nextToken();

				User userA = users.getOrDefault(idOfUserA, new User(idOfUserA, index++));
				User userB = users.getOrDefault(idOfUserB, new User(idOfUserB, index++));

				union(userA, userB);

				users.put(idOfUserA, userA);
				users.put(idOfUserB, userB);

				result.append(counts[find(userA.group)]).append("\n");
			}
		}
		System.out.println(result);
	}

	public static int find(int group) {
		if (groups[group] == group)
			return group;

		return groups[group] = find(groups[group]);
	}

	public static void union(User userA, User userB) {
		int fa = find(userA.group);
		int fb = find(userB.group);

		if (fa == fb)
			return;

		if (fa > fb) {
			groups[fa] = fb;
			counts[fb] += counts[fa];
			userA.group = fb;
		} else {
			groups[fb] = fa;
			counts[fa] += counts[fb];
			userB.group = fa;
		}
	}
}
