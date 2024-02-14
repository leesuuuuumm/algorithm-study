package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main_27172_수나누기게임 {

	private final static int MAX_CARD_VALUE = 1_000_000;
	static int N;
	static int[] cards, result;
	static List<Integer> values;
	static Set<Integer> distinct;


	public static void main(String[] args) throws Exception {
		N = read();
		cards = new int[MAX_CARD_VALUE + 1];
		Arrays.fill(cards, -1);
		result = new int[N];
		values = new ArrayList<>();
		distinct = new HashSet<>();
		for (int i = 0; i < N; i++) {
			int value = read();
			cards[value] = i;
			values.add(value);
		}
		Collections.sort(values);
		for (Integer value : values) {
			for (int i = 1; i <= Math.sqrt(value); i++) {
				if (value % i == 0) {
					if (distinct.contains(value / i)) {
						if (cards[value / i] == -1)
							continue;
						result[cards[value / i]]++;
						result[cards[value]]--;
					}
					if (i != value / i && distinct.contains(i)) {
						if (cards[i] == -1)
							continue;
						result[cards[i]]++;
						result[cards[value]]--;
					}
				}
			}
			distinct.add(value);
		}
		StringBuilder sb = new StringBuilder();
		for (int i : result) {
			sb.append(i).append(" ");
		}
		sb.setLength(sb.length() - 1);
		System.out.print(sb);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
