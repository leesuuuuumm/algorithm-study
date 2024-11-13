import java.io.*;
import java.util.*;

public class Main {
	static boolean[] v;
	static int[] minus;
	static int[] plus;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		minus = new int[N];
		plus = new int[N];
		v = new boolean[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			minus[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			plus[i] = Integer.parseInt(st.nextToken());
		}
		ans = 0;
		subset(0);
		System.out.println(ans);

	}
	static int ans;
	private static void subset(int cnt) {
		if(cnt == N) {
			int power = 100;
			int happy = 0;
			// true 인사함 
			for(int i=0;i<N;i++) {
				if(v[i]) {
					if(power-minus[i] <=0) break;
					power-=minus[i];
					happy+=plus[i];
				}

			}
			ans = Math.max(ans,happy);
			return;
		}
		
		v[cnt]= true;
		subset(cnt+1);
		v[cnt] = false;
		subset(cnt+1);
	}
}
