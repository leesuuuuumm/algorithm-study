import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[2000000];

		for (int i = 2; i < arr.length; i++) {
			arr[i] = i;
		}

		for (int i = 2; i <= Math.sqrt(arr.length); i++) {
			if (arr[i] == 0)
				continue;

			for (int j = i * 2; j < arr.length; j += i) {
				arr[j] = 0;
			}
		}

		int k = N;
		while (true) {
			StringBuilder sb = new StringBuilder();
			if (arr[k] != 0) {
				sb.append(arr[k]);
				
				if(sb.toString().equals(sb.reverse().toString())) {
					System.out.println(sb);
					return;
				}
			}
			k++;
		}
	}
}
