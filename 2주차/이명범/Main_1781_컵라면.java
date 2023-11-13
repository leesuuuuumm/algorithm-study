package boj;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_1781_컵라면 {

	static class Problem {
		int deadline;
		int reward;

		public Problem(int deadline, int reward) {
			this.deadline = deadline;
			this.reward = reward;
		}
	}

	static int N;
	static PriorityQueue<Problem> descDeadline;
	static PriorityQueue<Problem> maxRewardOfNow;

	public static void main(String[] args) throws Exception {
	    N = read();
		descDeadline = new PriorityQueue<>(Comparator.comparingInt(o -> -o.deadline));
		maxRewardOfNow = new PriorityQueue<>(Comparator.comparingInt(o -> -o.reward));
		for (int i = 0; i < N; i++) {
			int deadline = read();
			int reward = read();
			descDeadline.add(new Problem(deadline, reward));
		}
		int result = 0;
		for (int i = N; i >= 1; i--) {
			while (!descDeadline.isEmpty()) {
				if (descDeadline.peek().deadline < i)
					break;
				maxRewardOfNow.add(descDeadline.poll());
			}
			result += maxRewardOfNow.isEmpty() ? 0 : maxRewardOfNow.poll().reward;
		}
		System.out.println(result);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
