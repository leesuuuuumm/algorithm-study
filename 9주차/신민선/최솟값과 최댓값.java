/*
백준 2357
날짜 2024.01.01
*/
import java.io.*;
import java.util.*;

class SegmentTree{
	int[] arr;
	int[] tree_min;
	int[] tree_max;
	SegmentTree(int N,int size){
		arr=new int[N];
		tree_min=new int[size];
		tree_max=new int[size];
	}
	
	int init_min(int node,int start, int end) {
		if(start==end) {
			return tree_min[node]=arr[start];
		}
		return tree_min[node]=Math.min(init_min(node*2, start,(start+end)/2), init_min(node*2+1,(start+end)/2+1, end));
	}
	int init_max(int node,int start, int end) {
		if(start==end) {
			return tree_max[node]=arr[start];
		}
		return tree_max[node]=Math.max(init_max(node*2, start,(start+end)/2), init_max(node*2+1,(start+end)/2+1, end));
	}
	
	int min(int node,int start, int end, int left, int right) {
		if(end < left || right<start) {
			return 1000000001;
		}
		if(left<=start && end<=right) {
			return tree_min[node];
		}
		return Math.min(min(node*2, start, (start+end)/2, left, right), min(node*2+1, (start+end)/2+1, end, left, right));
	}
	int max(int node,int start, int end, int left, int right) {
		if(end < left || right<start) {
			return 0;
		}
		if(left<=start && end<=right) {
			return tree_max[node];
		}
		return Math.max(max(node*2, start, (start+end)/2, left, right), max(node*2+1, (start+end)/2+1, end, left, right));
	}
	
	void update(int node,int start, int end, int index, int val, int flag) {//flag==0이면 min, 1이면 max
		if(index<start || end>index)
			return;
		if(flag==0)
			tree_min[node]=Math.min(tree_min[node], val);
		else
			tree_max[node]=Math.max(tree_max[node], val);
		if(start!=end) {
			update(node*2, start, (start+end)/2, index, val, flag);
			update(node*2+1, (start+end)/2+1, end, index, val, flag);
		}
	}
	void print_min() {
		for(int k:tree_min) {
			System.out.printf("%d ",k);
		}
		System.out.println();
	}
	void print_max() {
		for(int k:tree_max) {
			System.out.printf("%d ",k);
		}
		System.out.println();
	}
}

public class Beakjoon_2357 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		
		int h=(int) Math.ceil(Math.log(N)/Math.log(2));
		int size=(int) Math.pow(2,h+1);
		SegmentTree tree=new SegmentTree(N,size);
		
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			int k=Integer.parseInt(st.nextToken());
			tree.arr[i]=k;
		}
		tree.init_min(1,0,N-1);
		tree.init_max(1,0,N-1);
		
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken())-1;
			int b=Integer.parseInt(st.nextToken())-1;
			int min=tree.min(1,0,N-1,a,b);
			int max=tree.max(1,0,N-1,a,b);
			sb.append(min+" "+max+"\n");
		}
		bw.write(sb.toString());
		bw.flush();

	}

}
