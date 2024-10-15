import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point>{
		int d;
		int s;
		int e;

		public Point(int d, int s, int e) {
			this.d = d;
			this.s = s;
			this.e = e;
		}

		public int compareTo(Point o) {
			if (this.d == o.d) {
				if (this.e == o.e) {
					return Integer.compare(this.s, o.s);
				}
				return Integer.compare(this.e, o.e);
			}

			return Integer.compare(this.d, o.d);
		}
		public String toString() {
			return this.d+" "+this.s+" "+this.e +"\n";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			ArrayList<Point> list = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken())));

			}
			Collections.sort(list);
		 
			int ans = 0;
			int cnt = 1;
			int e = list.get(0).e;

			for(int i=1;i<N;i++) {
				if(list.get(i).d != list.get(i-1).d) {
					e = list.get(i).e;
					ans+=cnt;
					cnt = 1;
				}
				if(e<=list.get(i).s) {
					cnt++;
					e = list.get(i).e;
				}
			}
			ans+=cnt;
			
			sb.append("Scenario #").append(t).append(":").append("\n").append(ans);
			if(t<T) {
				sb.append("\n").append("\n");
			}
		}
		System.out.print(sb);


	}

}
