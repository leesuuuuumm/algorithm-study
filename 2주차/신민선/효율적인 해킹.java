/*
알고리즘 코딩테스트 자바 8장
백준 1325
날짜 2023.11.15
*/
import java.io.*;
import java.util.*;
public class Beakjoon_1325 {
	static int N,M;
	static boolean visited[];
	static int answer[];
	static ArrayList<Integer>A[];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		A=new ArrayList[N+1];
		answer=new int[N+1];
		for(int i=1;i<=N;i++) {
			A[i]=new ArrayList<>();
		}
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken());
			int e=Integer.parseInt(st.nextToken());
			A[s].add(e);
		}
		for(int i=1;i<=N;i++) {
			visited=new boolean[N+1];
			BFS(i);
		}
		int max=0;
		for(int i=1;i<=N;i++) {
			max=Math.max(max,answer[i]);
		}
		for(int i=1;i<=N;i++) {
			if(answer[i]==max)
			System.out.println(i+" ");
		}
	}
	static void BFS(int Node) {
		Queue<Integer> q=new LinkedList<>();
		q.add(Node);
		visited[Node]=true;
		while(!q.isEmpty()) {
			int now=q.poll();
			for(int i:A[now]) {
				if(visited[i]==false) {
					visited[i]=true;
					answer[i]++;
					q.add(i);
				}
			}
		}
	}

}
