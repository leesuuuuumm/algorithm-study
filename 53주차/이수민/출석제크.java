import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[] sleep = new boolean[N+3];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<K;i++) {
			sleep[Integer.parseInt(st.nextToken())] = true;
		}
		boolean[] v = new boolean[N+3];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<Q;i++) {
			int num = Integer.parseInt(st.nextToken());
			if(sleep[num]) continue;
			for(int j=num;j<v.length;j+=num) {
				if(sleep[j]) continue;
				v[j] = true;
			}
		}

		int[] cnt = new int[N+3];
		for(int i=3;i<=N+2;i++) {
			
			if(v[i]) {
				cnt[i] = cnt[i-1];
			}else {
				cnt[i]+=cnt[i-1]+1;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			sb.append(cnt[e]-cnt[s-1]).append("\n");
		}
			System.out.println(sb);
	}
}
