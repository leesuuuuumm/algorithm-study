package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_1744_수묶기 {

	static int N;
	static PriorityQueue<Integer> ascend = new PriorityQueue<>(Comparator.reverseOrder());
	static PriorityQueue<Integer> descend = new PriorityQueue<>();


	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());

			if (num <= 0)
				descend.add(num);
			else
				ascend.add(num);
		}
		System.out.println(getResult(ascend) + getResult(descend));
	}

	private static int getResult(PriorityQueue<Integer> pq) {
		int result = 0;
		while (!pq.isEmpty()) {
			int a = pq.poll();

			if (pq.isEmpty()) {
				result += a;
				break;
			}

			int b = pq.poll();

			if (a * b < a + b) {
				result += a + b;
				continue;
			}

			result += a * b;
		}
		return result;
	}
}
