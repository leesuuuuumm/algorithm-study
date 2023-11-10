package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_2607_비슷한단어 {
	static final int NUMBER_OF_ALPHABETS = 26;
	static int N;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		String origin = br.readLine();
		int[] originSpells = new int[NUMBER_OF_ALPHABETS];
		for (int i = 0; i < origin.length(); i++) {
			originSpells[origin.charAt(i) - 'A']++;
		}

		int result = 0;
		for (int i = 0; i < N - 1; i++) {
			String target = br.readLine();
			int[] targetSpells = new int[NUMBER_OF_ALPHABETS];
			for (int j = 0; j < target.length(); j++) {
				targetSpells[target.charAt(j) - 'A']++;
			}
			if (isSimilarWord(originSpells, targetSpells))
				result++;
		}
		System.out.println(result);
	}

	private static boolean isSimilarWord(int[] originSpells, int[] targetSpells) {
		int[] origin = new int[NUMBER_OF_ALPHABETS];
		int[] target = new int[NUMBER_OF_ALPHABETS];

		for (int i = 0; i < NUMBER_OF_ALPHABETS; i++) {
			origin[i] = originSpells[i];
			target[i] = targetSpells[i];
		}

		if (hasOneDiff(origin, target))
			return true;

		for (int i = 0; i < NUMBER_OF_ALPHABETS; i++) {
			if (origin[i] > target[i]) {
				origin[i]--;
				break;
			}
		}
		for (int i = 0; i < NUMBER_OF_ALPHABETS; i++) {
			if (origin[i] < target[i]) {
				target[i]--;
				break;
			}
		}
		for (int i = 0; i < NUMBER_OF_ALPHABETS; i++) {
			if (origin[i] != target[i])
				return false;
		}
		return true;
	}

	private static boolean hasOneDiff(int[] origin, int[] target) {
		int count = 0;
		for (int i = 0; i < NUMBER_OF_ALPHABETS; i++) {
			int diff = Math.abs(origin[i] - target[i]);

			if (diff >= 2)
				return false;

			if (diff == 1)
				count++;
		}
		return count < 2;
	}
}
