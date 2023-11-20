/*
알고리즘 코딩테스트 자바 8장
백준 1043
날짜 2023.11.21
*/
import java.util.*;
import java.io.*;

public class Beakjoon_1043 {
	static int[] people;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		int M=sc.nextInt();
		int result=0;
		int[] party=new int[M];
		Set<Integer> set=new HashSet<>();
		people=new int[N+1];
		for(int i=1;i<=N;i++) {
			people[i]=i;
		}
		int k=sc.nextInt();
		int[] know=new int[k];//진실을 아는 사람 리스트
		for(int i=0;i<k;i++) {
			know[i]=sc.nextInt();
		}
		for(int i=0;i<M;i++) {
			int num=sc.nextInt();
			int p;
			int p_parent;
			if(num!=0) {
				p=sc.nextInt();
				p_parent=find(p);
				party[i]=p;
				for(int j=1;j<num;j++) {
					int b=sc.nextInt();
					union(p_parent,b);
				}
			}
		}
		for(int i=0;i<k;i++) {//진실을 아는 사람의 부모를 set에 저장.
			set.add(find(know[i]));
		}
		for(int i=0;i<M;i++) {//파티의 임의의 한명의 부모가 set에 있는지 확인.
			int maybe=find(party[i]);
			if(!set.contains(maybe)) {//진실을 아는 사람이 파티에 없음.
				result++;
			}
		}
		System.out.println(result);
	}
	static void union(int a, int b) {//a는 이미 부모로 들어옴.
		int b_parent=find(b);
		people[b_parent]=a;
	}
	static int find(int a) {
		if(people[a]==a)
			return a;
		return find(people[a]);
	}
}
