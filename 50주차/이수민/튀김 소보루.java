import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point>{
		int num;
		int time;
		
		public Point(int num, int time) {
			this.num = num;
			this.time = time;
		}
		
		public int compareTo(Point o) {
			if(this.time == o.time) {
				return Integer.compare(this.num, o.num);
			}
			return Integer.compare(this.time, o.time);
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		
		
		PriorityQueue<Point> pq = new PriorityQueue<>();
		
		int m = Integer.parseInt(br.readLine());
		int[] arr = new int[m];
		for(int i=0;i<m;i++) {
			arr[i] = Integer.parseInt(br.readLine());
			pq.offer(new Point(i+1,1));
		}
		
		int ans = pq.peek().num;
		for(int i=1;i<=n-s;i++) {
			Point cur = pq.poll();

			pq.offer(new Point(cur.num, cur.time+arr[cur.num-1]));
			if(i == (n-s)) {
				ans = cur.num;
				break;
			}
		}
		System.out.println(ans);
		
		
	}
}
