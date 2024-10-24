import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point>{
		int id;
		int time;
		int p;
		
		public Point(int id, int time, int p) {
			this.id = id;
			this.time = time;
			this.p = p;
		}
		
		public int compareTo(Point o) {
			if(this.p == o.p) {
				return Integer.compare(this.id, o.id);
			}
			return Integer.compare(o.p,this.p);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		PriorityQueue<Point> pq = new PriorityQueue<>();
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			pq.offer(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		
		StringBuilder sb  = new StringBuilder();
		while(T-->0) {
			if(pq.isEmpty()) break;
			
			Point cur = pq.poll();
			sb.append(cur.id).append("\n");
			
			if(cur.time > 1) {
				pq.offer(new Point(cur.id, cur.time-1,cur.p-1));
			}
			
		}
		System.out.println(sb);

	}
}
