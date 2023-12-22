/*
알고리즘 코딩테스트 자바 9장
백준 1068
날짜 2023.12.22
*/
import java.io.*;
import java.util.*;
public class Beakjoon_1068 {
	static int N;
	static int delete;
	static ArrayList<ArrayList<Integer>> tree;//자식 노드를 저장.
	static boolean[] visited;
	static int leaf=0;
	static int head=0;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		tree=new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<N;i++) {
			tree.add(new ArrayList<Integer>());
		}
		visited= new boolean[N];
		for(int i=0;i<N;i++) {
			int p=sc.nextInt();
			if(p==-1) {
				head=i;
			}
			else {
				tree.get(p).add(i);
			}
		}
		delete=sc.nextInt();
		DFS(head);
		System.out.println(leaf);
	}
	public static void DFS(int now) {
		if(now!=delete) {
			if(tree.get(now).size()==0) {
				leaf++;
				return;
			}
			if(tree.get(now).size()==1 && tree.get(now).get(0)==delete) {
				leaf++;
				return;
			}
			for(int next:tree.get(now)) {
				if(!visited[next]) {
					visited[next]=true;
					DFS(next);
				}
				
			}
		}
	}
}
