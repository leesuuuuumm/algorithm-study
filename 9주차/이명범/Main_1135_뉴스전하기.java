package boj;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
0  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
-1 0 0 1 1 1 2 2 3 3  4  4  5  5  6  7  7  8 12 13 14 16 16 16
 */
public class Main_1135_뉴스전하기 {

	static class Employee {
		int num;
		int score;

		public Employee(int num, int score) {
			this.num = num;
			this.score = score;
		}
	}
	static int N;
	static List<Integer>[] relations;

	public static void main(String[] args) throws Exception {
		N = read();
		relations = new List[N];
		for (int i = 0; i < N; i++) {
			relations[i] = new ArrayList<>();
		}
		for (int i = 0; i < N; i++) {
			if (i == 0) {
				read();
				continue;
			}
			relations[read()].add(i);
		}
		System.out.println(recur(0));
	}

	private static int recur(int cur) {
		if (relations[cur].isEmpty()) {
			return 0;
		}

		List<Employee> employees = new ArrayList<>();
		for (Integer next : relations[cur]) {
			int score = recur(next);
			employees.add(new Employee(next, score));
		}
		employees.sort(Comparator.comparing(o -> -o.score));
		int value = 1;
		int result = 0;
		for (Employee employee : employees) {
			employee.score += value++;
			result = Math.max(result, employee.score);
		}
		return result;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
