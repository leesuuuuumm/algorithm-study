/*
알고리즘 코딩테스트 자바 8장
백준 1976
날짜 2023.11.17
*/
import java.io.*;
import java.util.*;

public class Beakjoon_1976 {
	static int n;
	static int[] set;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		int m=sc.nextInt();//여행 계획 도시 수
		set=new int[n+1];//1부터 n까지.
		for(int i=1;i<=n;i++) {
			set[i]=i;
		}
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				int c=sc.nextInt();
				if(c==1) {//i,j가 연결된 도시임. union 작업.
					union(i,j);
				}
			}
		}
		int parent=find(sc.nextInt());
		boolean check=true;
		for(int i=0;i<m-1;i++) {
			if(parent!=find(sc.nextInt())) {
				check=false;
				break;
			}
		}
		if(check)
			System.out.println("YES");
		else
			System.out.println("NO");
	}
	static void print() {
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				System.out.printf("%d ",set[i]);
			}
			System.out.println();
		}
	}
	static void union(int a, int b) {
		int a_parent=find(a);
		int b_parent=find(b);
		set[b_parent]=a_parent;
	}
	static int find(int a) {
		if(set[a]==a)
			return a;
		return set[a]=find(set[a]);
	}
}
