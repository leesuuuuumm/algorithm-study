package boj;

import java.util.Arrays;
import java.util.Comparator;

public class Main_2624_동전바꿔주기 {

	static class Coin {
		int value;
		int amount;

		public Coin(int value, int amount) {
			this.value = value;
			this.amount = amount;
		}
	}
	static int T, k;
	static int[] dp;
	static Coin[] coins;

	public static void main(String[] args) throws Exception {
		input();
		for (int i = 1; i < k; i++) {
			int[] counts = new int[T + 1];
			for (int j = 1; j <= coins[i].amount; j++) {
				if (coins[i].value * j > T)
					break;
				counts[coins[i].value * j]++;
				for (int l = coins[i].value * j; l <= T; l++) {
					counts[l] += dp[l - coins[i].value * j];
				}
			}
			for (int j = 0; j <= T; j++) {
				dp[j] += counts[j];
			}
		}
		System.out.println(dp[T]);
	}

	private static void input() throws Exception {
		T = read();
		k = read();
		dp = new int[T + 1];
		coins = new Coin[k];
		for (int i = 0; i < k; i++) {
			int value = read();
			int amount = read();
			coins[i] = new Coin(value, amount);
		}
		Arrays.sort(coins, Comparator.comparing(o -> o.value));
		for (int i = 1; i <= coins[0].amount; i++) {
			if (coins[0].value * i > T)
				break;
			dp[coins[0].value * i]++;
		}
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
