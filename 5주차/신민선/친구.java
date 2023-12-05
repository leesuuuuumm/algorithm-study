/*
백준 1058
날짜 2023.12.05
*/
import java.io.*;
import java.util.*;
public class Beakjoon_1058 {

	static int N;
	static char[][] friend;
	static boolean[] visited;
	static int maxCount = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		friend = new char[N][N];
		for (int i = 0; i < N; i++) {
			friend[i] = br.readLine().toCharArray();
		}
		
		//--------입력

		//완탐
		//각 사람마다 2-친구 수가 몇명인지 알아내서 가장 큰 수 출력
		for (int i = 0; i < N; i++) {
			
			visited = new boolean[N];
			visited[i] = true;

			Queue<Integer> q = new LinkedList<>();
			q.add(i);
			
			int depth = 0;
			int count = 0;

			while (depth < 2) { //한 친구 건너서 알게되는 사람까지만 count하기 때문에
				
				int size = q.size();
				for (int j = 0; j < size; j++) {
					
					int n = q.poll();
					
					for (int k = 0; k < N; k++) {
						
						if (friend[n][k] == 'Y' && !visited[k]) {
							q.add(k);
							count++;
							visited[k] = true;
						}
					}
				}
				depth++;
			}
			if(maxCount < count) {
				maxCount = count;
			}
		}

		System.out.println(maxCount);

	}

}
