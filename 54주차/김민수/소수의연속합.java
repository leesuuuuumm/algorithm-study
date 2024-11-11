import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//투포인터


public class 소수의연속합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] isNotPrime = new boolean[N + 1];
		ArrayList<Integer> prime = new ArrayList<>();
		for (int i = 2; i <= N; i++) {
			if (!isNotPrime[i]) {
				prime.add(i);
				for (int j=i*2;j<=N;j+=i) {
					isNotPrime[j] = true;
				}
			}
		}

		int start = 0;
		int end = 0;
		int sum = 0;
		int answer = 0;
		while (end <= prime.size()) {
			if (sum == N) {
				answer += 1;
				sum -= prime.get(start++);
			} else if (sum < N) {
				if (end == prime.size())
					break;

				sum += prime.get(end++);
			} else {
				sum -= prime.get(start++);
			}
		}
		System.out.println(answer);

	}
}
