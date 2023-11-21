package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main_5052_전화번호목록 {

	static class Node {
		Node[] children;
		int depth;
		boolean hasValue;

		public Node(int depth) {
			this.children = new Node[10];
			this.depth = depth;
			this.hasValue = false;
		}

		public boolean add(String num) {
			if (hasValue)
				return false;

			if (depth == num.length() - 1) {
				hasValue = true;
				return true;
			}

			if (children[num.charAt(depth + 1) - '0'] == null)
				children[num.charAt(depth + 1) - '0'] = new Node(depth + 1);

			return children[num.charAt(depth + 1) - '0'].add(num);
		}
	}

	static int T, N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			Node[] trie = new Node[10];
			for (int i = 0; i < 10; i++) {
				trie[i] = new Node(0);
			}
			boolean isSuccess = true;
			String[] nums = new String[N];
			for (int i = 0; i < N; i++) {
				nums[i] = br.readLine();
			}
			Arrays.sort(nums, Comparator.comparing(String::length));
			for (int i = 0; i < N; i++) {
				String num = nums[i];
				if (!trie[num.charAt(0) - '0'].add(num)) {
					isSuccess = false;
					break;
				}
			}
			result.append(isSuccess ? "YES" : "NO").append("\n");
		}
		System.out.println(result);
	}
}
