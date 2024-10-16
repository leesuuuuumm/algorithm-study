import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] arr = new int[K][2];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		
		int ans = 0;
		for (int i = 0; i < K; i++) {
			int cnt = 1;
			for(int j=0;j<K;j++) {
				
				int mr = arr[i][0];
				int mc = arr[j][1];
				cnt = 0;
				
				for(int[] tmp:arr) {
					if(mr<=tmp[0] && mr+L>=tmp[0] && mc<=tmp[1] && mc+L>=tmp[1] ) {
						cnt++;
					}
				}
				ans = Math.max(ans,cnt);
				
			}
		}
		
		System.out.println(K-ans);

	}
}
