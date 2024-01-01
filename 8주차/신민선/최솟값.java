/*
백준 10868
날짜 2023.12.29
*/
import java.io.*;
import java.util.*;
class SegmentTreeMin{
	int[] a;
	int[] tree;
	SegmentTreeMin(int N, int size){
		a=new int[N];
		tree=new int[size];
	}
	
	int init(int node, int start, int end) {
		if(start==end) {
			return tree[node]=a[start];
		}
		else {
			return tree[node]=Math.min(init(node*2, start,(start+end)/2),init(node*2+1, (start+end)/2+1,end));
		}
	}
	
	int min(int node,int start, int end, int left, int right) {
		if(left>end || right<start) {
			return 1000000000;
		}
		if(left<=start && end<=right) {
			return tree[node];
		}
		return Math.min(min(node*2, start, (start+end)/2,left,right), min(node*2+1,(start+end)/2+1,end, left, right));
	}
	
	void update(int node,int start, int end, int index, int newVal) {
		if(index< start || index > end) return;
		tree[node]=Math.min(tree[node],newVal);
		if(start!=end) {
			update(node*2, start, (start+end)/2, index, newVal);
			update(node*2+1, (start+end)/2+1, end, index, newVal);
		}
	}
	
}
public class Beakjoon_10868 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		int M=sc.nextInt();		
		int h=(int) Math.ceil(Math.log(N)/Math.log(2));//log2(n)
		int size=(int)Math.pow(2,h+1);//1부터 시작하므로 -1 안해줌
		SegmentTreeMin tree=new SegmentTreeMin(N,size);
		
		for(int i=0;i<N;i++) {
			tree.a[i]=sc.nextInt();
		}
		tree.init(1,0,N-1);
		for(int i=0;i<M;i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			System.out.println(tree.min(1,0,N-1,a-1,b-1));
		}
	}

}
