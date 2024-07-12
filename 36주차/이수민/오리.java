import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] ch = br.readLine().toCharArray();
		boolean[] v = new boolean[ch.length];
		int k = 0;
        
		char[] arr = { 'q', 'u', 'a', 'c', 'k' };
		int cnt = 0;
        
		if (ch.length % 5 != 0 || ch[0] != 'q') {
			System.out.println(-1);
			return;
		}

		for (int i = 0; i < ch.length; i++) {
			ArrayList<Character> list = new ArrayList<>();
			for (int j = i; j < ch.length; j++) {
				if (!v[j] && ch[j] == arr[k]) {
					k++;
					list.add(ch[j]);
					v[j] = true;
					if (k == 5) k = 0;
				}
			}

        if (list.size() != 0) {
				  if (list.get(list.size() - 1) != 'k') {
					  System.out.println(-1);
					  return;
				  }
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
