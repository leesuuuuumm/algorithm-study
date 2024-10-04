import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] arr = new String[n];
		for (int i = 0; i < n; i++) {
			arr[i] = br.readLine();
		}
		Arrays.sort(arr);

		int cnt = 0;
		c: for (int i = 0; i < n; i++) {
			String[] str1 = arr[i].split("");

			e: for (int j = i + 1; j < n; j++) {
				if (str1.length > arr[j].length())
					continue;

				String[] str2 = arr[j].split("");

				for (int k = 0; k < str1.length; k++) {
					if (!str1[k].equals(str2[k]))
						continue e;
				}
				cnt++;
				continue c;

			}
		}
		System.out.println(n - cnt);

	}
}
