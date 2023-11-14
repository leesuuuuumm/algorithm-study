/*
알고리즘 코딩테스트 자바 7장
Beakjoon_1033
날짜 2023.11.14
*/
import java.io.*;
import java.util.*;
class Beakjoon_1033_Node{
	int b;
	int p;
	int q;
	Beakjoon_1033_Node(int b, int p, int q){
		this.b=b;
		this.p=p;
		this.q=q;
	}
}
public class Beakjoon_1033 {
	static ArrayList<Beakjoon_1033_Node>[] A;
	static long lcm;
	static boolean visited[];
	static long D[];
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		A=new ArrayList[N];
		visited=new boolean[N];
		D=new long[N];
		lcm=1;
		for(int i=0;i<N;i++) {
			A[i]=new ArrayList<Beakjoon_1033_Node>();
		}
		for(int i=0;i<N-1;i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			int p=sc.nextInt();
			int q=sc.nextInt();
			A[a].add(new Beakjoon_1033_Node(b,p,q));
			A[b].add(new Beakjoon_1033_Node(a,q,p));
			lcm*=(p*q/gcd(p,q));
		}
		D[0]=lcm;
		DFS(0);
		long result=D[0];
		for(int i=0;i<N;i++) {
			result=gcd(result,D[i]);
		}
		for(int i=0;i<N;i++) {
			System.out.print(D[i]/result+" ");
		}
	}
	static long gcd(long a,long b) {
		if(b==0)
			return a;
		return gcd(b,a%b);
	}
	static void DFS(int node) {
		visited[node]=true;
		for(Beakjoon_1033_Node n:A[node]) {
			int next=n.b;
			if(!visited[next]) {
				D[next]=D[node]*n.q/n.p;
				DFS(next);
			}
		}
	}

}
