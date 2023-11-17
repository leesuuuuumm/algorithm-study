/*
알고리즘 코딩테스트 자바 8장
백준 1717
날짜 2023.11.17
*/
import java.io.*;
import java.util.*;

public class Beakjoon_1717 {
	static int n;
	static int m;
	static int[] set;
	public static void main(String[] args) {
		Scanner sc=new Scanner (System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		set=new int[n+1];
		for(int i=0;i<=n;i++) {
			set[i]=i;
		}
		for(int i=0;i<m;i++) {
			int q=sc.nextInt();
			int a=sc.nextInt();//부모
			int b=sc.nextInt();//자식
			if(q==0) {//합집합
				union(a,b);
			}
			else {//같은 집합 확인
				if(find(a)==find(b))
					System.out.println("YES");
				else {
					System.out.println("NO");
				}
			}
		}
	}
	static void print() {
		for(int i:set) {
			System.out.printf("%d ",i);
		}
		System.out.println();
	}
	static void union(int a, int b) {
		int a_parent=find(a);
		int b_parent=find(b);
		set[b_parent]=a_parent;
	}
	static int find(int a) {
		if(set[a]==a) {
			return a;
		}
		return set[a]=find(set[a]);
	}
}
