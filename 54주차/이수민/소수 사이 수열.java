import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		boolean[] arr = new boolean[1299710];

		arr[0] = true;
		arr[1] = true;

		for (int i = 2; i <= Math.sqrt(1299709); i++) {
			if (!arr[i])
				for (int j = i * i; j <= 1299709; j += i) {
					arr[j] = true;
				}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			int K = Integer.parseInt(br.readLine());
			if(!arr[K]) sb.append(0).append("\n");
			else {
				int l = K;
				int r = K;
				
				while(true) {
					if(!arr[l] && !arr[r]) break;
					
					if(arr[l])l--;
					if(arr[r])r++;
				}
				
				sb.append(r-l).append("\n");
				
			}

		}
		System.out.println(sb);

	}
}
