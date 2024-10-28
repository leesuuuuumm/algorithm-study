import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=  new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long[] arr=  new long[N];
		for(int i=0;i<N;i++) {
			arr[i] =Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		// 최소 시간은 1초로 통과되는 1개의 심사대
		//  최대시간은 가장 큰 수 * 인원수 만큼 있는 애
		
		long L = 1;
		long R = arr[N-1]*M;
		
		
		while(L<=R) {
			long mid = (L+R)/2;
			long sum = 0;
			for(int i=0;i<N;i++) {
				sum += (mid/arr[i]);
				if(sum >=M) break;
			}
			if(sum<M) {
				L = mid+1;
			}else {
				R = mid-1;
			}
		}
		System.out.println(L);
		
		
		
		
		
		
		
		
	}
}
