/*
백준 2042
날짜 2023.12.28
*/
import java.io.*;
import java.util.*;
class SegmentTree{
	long[] a;
	long[] tree;
	SegmentTree(long[] a, int size){
		this.a=a;
		tree=new long[size];
	}
	long init(int node, int start, int end) {
		if(start==end) {
			return tree[node]=a[start];
		}
		else {
			return tree[node]=init(node*2,start, (start+end)/2) + init(node*2+1,(start+end)/2+1, end);
		}
	}
	long sum(int node, int start, int end, int left, int right) {
		if(left>end || right < start) {
			return 0;
		}
		if(left<=start && end<=right) {
			return tree[node];
		}
		return sum(node*2,start,(start+end)/2,left,right)+ sum(node *2+1,(start+end)/2+1,end,left, right);
		
	}
	
	void update(int node, int start, int end, int index, long diff) {
		if(index<start || index >end) return;
		tree[node]=tree[node]+diff;
		if(start!=end) {
			update(node*2, start, (start+end)/2, index, diff);
			update(node*2+1,(start+end)/2+1, end,index,diff);
		}
	}
	void printa() {
		for(long i:a) {
			System.out.printf("%d " ,i);
		}
		System.out.println();
	}
	void print() {
		for(long i:tree) {
			System.out.printf("%d " ,i);
		}
		System.out.println();
	}
}
public class Beakjoon_2042 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		int M=sc.nextInt();
		int K=sc.nextInt();
		
		long[] list=new long[N];
		for(int i=0;i<N;i++) {
			list[i]=sc.nextLong();
		}
		
		int h=(int) Math.ceil(Math.log(N)/Math.log(2));//log2(n)
		int size=(int)Math.pow(2,h+1);//1부터 시작하므로 -1 안해줌
		SegmentTree tree=new SegmentTree(list,size);
		tree.init(1,0,N-1);
		
		for(int i=0;i<M+K;i++) {
			int a=sc.nextInt();
			if(a==1) {//변경
				int b=sc.nextInt()-1;
				long c=sc.nextInt();
				long diff=c-tree.a[b];
				tree.a[b]=c;
				list[b]=c;
				tree.update(1,0,N-1,b,diff);
				//diff=새로운 값-원래 값, 원래 값은 a배열에 저장되어있음. a[k]값을 val로 변경하고 싶으면 
				//diff=val-a[k];
				//a[k]=val;
				//update(1,0,n-1,k,diff)
			}
			else {//구간합
				int b=sc.nextInt()-1;
				int c=sc.nextInt()-1;
				//a[k1]부터 a[k2]까지의 합을 구하고 싶으면
				long sum=tree.sum(1,0,N-1,b, c);
				System.out.println(sum);
			}
		}
	}

}
