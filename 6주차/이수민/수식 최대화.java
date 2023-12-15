import java.util.*;
class Solution {
 	static boolean[] v;
	static char[] selected;
	static char[] op = { '+', '-', '*' };
	static long max = Integer.MIN_VALUE;

	public long solution(String expression) {

		v = new boolean[3];
		selected = new char[3];

		permutation(0, expression);
		return max;
	}

	private void permutation(int cnt, String expression) {
		if (cnt == 3) {
			LinkedList<Character> opList = new LinkedList<>();
			LinkedList<Long> list = new LinkedList<>();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < expression.length(); i++) {
				if (expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*') {
					opList.add(expression.charAt(i));
					list.add(Long.parseLong(sb.toString()));
					sb.setLength(0);
				} else {
					sb.append(expression.charAt(i));
				}
			}
			list.add(Long.parseLong(sb.toString()));
			// for(int i=0;i<3;i++){
			// System.out.print(selected[i]+" ");
			// }
			// System.out.println();
			// System.out.println(opList);
			for (int i = 0; i < 3; i++) {
				while (opList.size() != 0) {
					int idx = opList.indexOf(selected[i]);
					// System.out.println(idx);
					if (idx == -1) {
						break;
					} else {
						list.set(idx, operation(selected[i], list.get(idx), list.get(idx + 1)));
						list.remove(idx + 1);
						opList.remove(idx);
						idx++;
					}
				}

			}

			max = Math.max(max, Math.abs(list.get(0)));

			return;
		}

		for (int i = 0; i < 3; i++) {
			if (v[i])
				continue;

			selected[cnt] = op[i];
			v[i] = true;
			permutation(cnt + 1, expression);
			v[i] = false;
		}
	}

	private long operation(char op, long i, long j) {
		long ans = 0;
		switch (op) {
		case '+':
			ans = i + j;
			break;
		case '-':
			ans = i - j;
			break;
		case '*':
			ans = i * j;
			break;
		}
		return ans;
	}

}
