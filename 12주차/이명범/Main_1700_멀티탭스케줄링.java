package boj;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Main_1700_멀티탭스케줄링 {

	static int N, K;
	static Queue<Integer>[] queues;
	static List<Integer> order;

	public static void main(String[] args) throws Exception {
		N = read();
		K = read();
		queues = new Queue[K + 1];
		order = new ArrayList<>();
		for (int i = 1; i <= K; i++) {
			queues[i] = new ArrayDeque<>();
		}
		for (int i = 0; i < K; i++) {
			int item = read();
			queues[item].add(i);
			order.add(item);
		}
		Set<Integer> used = new HashSet<>();
		int count = 0;
		for (Integer item : order) {
			if (used.contains(item)) {
				queues[item].poll();
				continue;
			}
			if (used.size() < N) {
				used.add(item);
				queues[item].poll();
				continue;
			}
			int removedIdx = -1;
			int removedItem = -1;
			for (Integer candidate : used) {
				if (queues[candidate].isEmpty()) {
					removedItem = candidate;
					break;
				}
				removedIdx = Math.max(removedIdx, queues[candidate].peek());
				removedItem = order.get(removedIdx);
			}
			used.remove(removedItem);
			used.add(item);
			queues[item].poll();
			count++;
		}
		System.out.println(count);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
