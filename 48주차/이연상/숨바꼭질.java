// [BOJ] 숨바꼭질

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int max = 100000;
		boolean[] visited = new boolean[max+1];
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(N);
		
		int time=0;
    	while(true) {
			int size=queue.size();
			for (int i = 0; i < size; i++) {
				int cur = queue.poll();
				visited[cur]=true;
				
				if(cur==K) break ex;
				if(cur*2 <= max && !visited[cur*2]) queue.offer(cur*2);
				if(cur-1 >= 0 && !visited[cur-1]) queue.offer(cur-1);
				if(cur+1 <= max && !visited[cur+1]) queue.offer(cur+1);
			}
			time++;
		}
		System.out.println(time);
	}
}