import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//투포인터

public class 합이0 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] input = new int[N];
		String[] st = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st[i]);
		}

		Arrays.sort(input);
		long count = 0;
		for (int i = 0; i < N; i++) {
			if (input[i] > 0)
				break;
			int fix = input[i];

			int left = i + 1;
			int right = N - 1;

			while (left < right) {
				int sum = input[left] + input[right];
				if (sum + fix == 0) {

					if (input[left] == input[right]) {
						int n = right - left + 1;
						int add = n * (n - 1) / 2;
						count += add;
						break;
					}
					int leftCount = 0;

					int leftTemp=left;
					while (input[leftTemp] == input[left]) {
						leftTemp++;
						leftCount += 1;
					}

					int rightCount = 0;
					int rightTemp=right;
					while (input[rightTemp] == input[right]) {
						rightTemp--;
						rightCount += 1;
					}

					count += (long)leftCount * rightCount;
					left=leftTemp;
					right=rightTemp;

				} else if (sum + fix > 0) {
					right--;
				} else {
					left++;
				}
			}
		}
		System.out.println(count);
	}

}
