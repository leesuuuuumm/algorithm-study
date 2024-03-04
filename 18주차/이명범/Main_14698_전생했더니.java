package boj;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main_14698_전생했더니 {
	private static final long DIVISION = 1_000_000_007L;

	static int T, N;
	static long result;
	static PriorityQueue<Long> slimes;

	public static void main(String[] args) throws Exception {
		T = (int) read();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			N = (int) read();
			result = 1L;
			slimes = new PriorityQueue<>();
			for (int j = 0; j < N; j++) {
				slimes.add(read());
			}
			List<Long> energies = new ArrayList<>();
			for (int j = 0; j < N - 1; j++) {
				Long e1 = slimes.poll();
				Long e2 = slimes.poll();
				Long energy = (e1 * e2);

				energies.add(energy % DIVISION);
				slimes.add(energy);
			}
			for (Long energy : energies) {
				result = (result * energy) % DIVISION;
			}
			sb.append(result).append("\n");
		}
		System.out.print(sb);
	}

	private static long read() throws Exception {
	    long c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
