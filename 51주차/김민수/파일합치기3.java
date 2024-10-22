import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 파일합치기3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T=Integer.parseInt(st.nextToken());
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<T;i++){
			st = new StringTokenizer(br.readLine());
			int k=Integer.parseInt(st.nextToken());
			long sum=0;
			PriorityQueue<Long> pq=new PriorityQueue<>();
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<k;j++){
				long s=Long.parseLong(st.nextToken());
				pq.add(s);
			}
			while(pq.size()>1){
				long first=pq.poll();
				long second=pq.poll();
				long add=first+second;
				sum+=add;
				pq.add(add);
			}
			sb.append(sum).append("\n");
		}
		System.out.println(sb);
	}
}
