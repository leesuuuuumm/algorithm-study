/*
알고리즘 코딩테스트 자바 8장
백준 2251
날짜 2023.11.16
*/
import java.io.*;
import java.util.*;

public class Beakjoon_2251 {
static int[] Sender = {0, 0, 1, 1, 2, 2};
	static int[] Receiver = {1, 2, 0, 2, 0, 1};
	static boolean visit[][]; 
	static boolean[] answer;
	static int[] now;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		now = new int[3];  
		for(int i = 0; i < now.length; i++) {
			now[i] = Integer.parseInt(st.nextToken());
		}
		visit = new boolean[201][201];
		answer = new boolean[201];
		BFS();
		for(int i = 0; i < answer.length; i++) {
			if(answer[i]) {
				System.out.print(i + " ");
			}
		}
	}
	private static void BFS() {
		Queue<AB> que = new LinkedList<>();
		que.add(new AB(0, 0));
		visit[0][0] = true;
		answer[now[2]] = true;
		while(!que.isEmpty()) {
			AB p = que.poll();
			int A = p.A;
			int B = p.B;
			int C = now[2] - A - B;      
			for(int k = 0; k < 6; k++) {  
				int[] next = {A, B, C};
				next[Receiver[k]] += next[Sender[k]];
				next[Sender[k]] = 0;
				if(next[Receiver[k]] > now[Receiver[k]]) {  
					next[Sender[k]] = next[Receiver[k]] - now[Receiver[k]];
					next[Receiver[k]] = now[Receiver[k]]; 
				}
				if(!visit[next[0]][next[1]]) {      
					visit[next[0]][next[1]] = true;
					que.add(new AB(next[0], next[1]));
					if(next[0] == 0) {  
						answer[next[2]] = true;
					}
				}
			}
		}
	}
}
class AB {
	int A;
	int B;
	public AB(int A, int B) {
		this.A = A;
		this.B = B;
	}
}
