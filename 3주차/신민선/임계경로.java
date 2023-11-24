/*
알고리즘 코딩테스트 자바 8장
백준 1948
날짜 2023.11.23
*/
import java.io.*;
import java.util.*;
class Beakjoon_1948_Node{
	int target;
	int data;
	Beakjoon_1948_Node(int target, int data){
		this.target=target;
		this.data=data;
	}
}
public class Beakjoon_1948 {
	static ArrayList<ArrayList<Beakjoon_1948_Node>> map;
	static ArrayList<ArrayList<Beakjoon_1948_Node>> rev_map;
	static int[] depth;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		int m=Integer.parseInt(st.nextToken());
		map=new ArrayList<>();
		rev_map=new ArrayList<>();
		depth=new int[n+1];
		int[] sum=new int[n+1];
		for(int i=0;i<=n;i++) {
			map.add(new ArrayList<>());
			rev_map.add(new ArrayList<>());
		}
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken());
			int e=Integer.parseInt(st.nextToken());
			int t=Integer.parseInt(st.nextToken());
			map.get(s).add(new Beakjoon_1948_Node(e,t));
			rev_map.get(e).add(new Beakjoon_1948_Node(s,t));
			depth[e]++;
		}
		st=new StringTokenizer(br.readLine());
		int start=Integer.parseInt(st.nextToken());
		int end=Integer.parseInt(st.nextToken());
		Queue<Integer> q=new LinkedList<>();
		q.add(start);
		while(!q.isEmpty()) {
			int now=q.poll();
			for(Beakjoon_1948_Node next: map.get(now)) {
				depth[next.target]--;
				sum[next.target]=Math.max(sum[next.target], sum[now]+next.data);
				if(depth[next.target]==0)
					q.add(next.target);
			}
		}
		
		Queue<Integer> rev_q=new LinkedList<>();
		boolean [] visited=new boolean[n+1];
		rev_q.add(end);
		visited[end]=true;
		int result=0;
		while(!rev_q.isEmpty()) {
			int now=rev_q.poll();
			visited[now]=true;
			for(Beakjoon_1948_Node prev:rev_map.get(now)) {
				if(prev.data+sum[prev.target]==sum[now]) {
					result++;
					if(visited[prev.target]==false) {
						visited[prev.target]=true;
						rev_q.add(prev.target);
					}
				}
			}
		}
		System.out.println(sum[end]);
		System.out.println(result);
	}

}
