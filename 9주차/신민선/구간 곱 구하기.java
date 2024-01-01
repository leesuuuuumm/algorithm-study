/*
백준 11505
날짜 2023.12.29
*/
import java.io.*;
import java.util.*;
class SegmentTreeMultiple_Beakjoon_11505{
	int[] a;
	long[] tree;
	int MOD=1000000007;
	SegmentTreeMultiple_Beakjoon_11505(int N, int size){
		a=new int[N];
		tree=new long[size];
	}
	
	long init(int node, int start, int end) {
		if(start==end) {
			return tree[node]=a[start];
		}
		else {
			return tree[node]=(init(node*2, start,(start+end)/2)*init(node*2+1, (start+end)/2+1,end))%MOD;
		}
	}
	
	long multiple(int node,int start, int end, int left, int right) {
		if(left>end || right<start) {//범위 밖. 영향 없어야함.
			return 1;
		}
		if(left<=start && end<=right) {
			return tree[node];
		}
		return (multiple(node*2, start, (start+end)/2,left,right)*multiple(node*2+1,(start+end)/2+1,end, left, right))%MOD;
	}
	
	long update(int node,int start, int end, int index, long newVal) {
		if(index< start || index > end) //변경에 영향 없는 노드들. 
			return tree[node];
		if(start==end) {//리프노드. 값이 변경된 노드
			return tree[node]=newVal;
		}
		return tree[node]=(update(node*2, start, (start+end)/2, index, newVal))*(update(node*2+1, (start+end)/2+1, end, index, newVal))%MOD;
	}
	void print() {
		for(long data:tree) {
			System.out.printf("%d ",data);
		}
		System.out.println();
	}
}
public class Beakjoon_11505 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		int M=sc.nextInt();
		int K=sc.nextInt();
		
		int h=(int) Math.ceil(Math.log(N)/Math.log(2));//log2(n)
		int size=(int)Math.pow(2,h+1);//1부터 시작하므로 -1 안해줌
		SegmentTreeMultiple_Beakjoon_11505 tree=new SegmentTreeMultiple_Beakjoon_11505(N,size);
		
		for(int i=0;i<N;i++) {
			tree.a[i]=sc.nextInt();
		}
		tree.init(1,0,N-1);
		//tree.print();
	
		for(int i=0;i<M+K;i++) {
			int a=sc.nextInt();
			if(a==1) {//변경
				int b=sc.nextInt()-1;
				int c=sc.nextInt();

				tree.a[b]=c;
				tree.update(1,0,N-1,b,c);
				//tree.print();
				//diff=새로운 값-원래 값, 원래 값은 a배열에 저장되어있음. a[k]값을 val로 변경하고 싶으면 
				//diff=val-a[k];
				//a[k]=val;
				//update(1,0,n-1,k,diff)
			}
			else {//구간합
				int b=sc.nextInt()-1;
				int c=sc.nextInt()-1;
				//a[k1]부터 a[k2]까지의 합을 구하고 싶으면
				long mul=tree.multiple(1,0,N-1,b, c);
				System.out.println(mul);
			}
	}

	}
}
