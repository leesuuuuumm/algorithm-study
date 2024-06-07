import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());

		char[] ch = br.readLine().toCharArray();
		int[] arr = new int[n];
		int answer = 0;

		for (int i = 1; i < n - 1; i++) {
			if (ch[i]=='O' && ch[i + 1] == 'I') {
				arr[i + 1] = arr[i - 1] + 1;

				if (arr[i + 1] >= N && ch[i - 2* N + 1]=='I') {
					answer++;
				}
			}
		}
		System.out.println(answer);

	}
}
