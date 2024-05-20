import java.util.*;
import java.io.*;

public class Main {

	static Queue<Integer> que;
	static int[][] family;
	static int[] cnt;
	static int start, end, num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken()); 
		end = Integer.parseInt(st.nextToken()); 
		num = Integer.parseInt(br.readLine());

		family = new int[n + 1][n + 1];
		cnt = new int[n + 1]; 

		for (int i = 0; i < num; i++) {
			st = new StringTokenizer(br.readLine()); 
			int f1 = Integer.parseInt(st.nextToken());
			int f2 = Integer.parseInt(st.nextToken());
			family[f1][f2] = 1;
			family[f2][f1] = 1;
		}


		que = new LinkedList<>();
		bfs();

		System.out.println(cnt[end] == 0 ? -1 : cnt[end]);
	}
	private static void bfs() {
		que.offer(start);
		a: while (!que.isEmpty()) {
			int cur = que.poll();
			for (int i = 1; i < cnt.length; i++) {

				if (family[cur][i] == 1 && cnt[i] == 0) {
					que.offer(i);
					cnt[i] = cnt[cur] + 1;
					
					if(i == start) {
						cnt[i] = 0;
					}
					if (i == end) {
						break a;
					}
				}
			}

		}

	}

}
