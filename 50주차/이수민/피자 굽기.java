import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] nrr = new int[N];
		
		for(int i=0;i<N;i++) {
			nrr[i] = Integer.parseInt(st.nextToken());
			if(i >=1 && nrr[i-1]<nrr[i]) {
				nrr[i] = nrr[i-1];
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		int[] drr = new int[D];
		
		for(int i=0; i<D;i++) {
			drr[i] = Integer.parseInt(st.nextToken());
		}
		int ans = 0;
		int j = 0;
		for(int i=N-1;i>=0;i--) {
			if(nrr[i]>=drr[j]) {
				j++;
				ans = i;
			}
			
			if(j==D) break;
		}
		System.out.println(j==D?ans+1:0);
		
	}
}
