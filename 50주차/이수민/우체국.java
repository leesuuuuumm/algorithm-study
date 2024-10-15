import java.io.*;
import java.util.*;

public class Main {
	static class Point{
		int loc;
		long cnt;
		
		public Point(int loc, long cnt) {
			this.loc = loc;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		Point[] sum = new Point[N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr,new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0],o2[0]);
			}
		});
		
		sum[0] = new Point(arr[0][0],arr[0][1]);
		for(int i=1;i<N;i++) {
			sum[i] = new Point(arr[i][0], sum[i-1].cnt+arr[i][1]);
		}
		
		long mid = ((sum[N-1].cnt % 2) ==0?(sum[N-1].cnt /2):(sum[N-1].cnt /2)+1);
		int ans = 0;
		for(int i=0;i<N;i++) {
			if(sum[i].cnt>=mid) {
				ans = sum[i].loc;
				break;
			}
		}
		System.out.println(ans);
		
		
	}
	
}
