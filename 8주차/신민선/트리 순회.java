/*
백준 1991
날짜 2023.12.27
*/
import java.io.*;
import java.util.*;

class Node_Beakjoon_1991{
	char data;
	Node_Beakjoon_1991 left;
	Node_Beakjoon_1991 right;
	Node_Beakjoon_1991(char data){
		this.data=data;
	}
}
class Tree{
	Node_Beakjoon_1991 root;
	
	void createNode(char data, char left_data, char right_data) {
		if(root==null) {
			root=new Node_Beakjoon_1991(data);
			
			if(left_data!='.') {
				root.left=new Node_Beakjoon_1991(left_data);
			}
			if(right_data!='.') {
				root.right=new Node_Beakjoon_1991(right_data);
			}
		}
		else {
			searchNode(root,data,left_data, right_data);
		}
	}
	
	void searchNode(Node_Beakjoon_1991 root, char data, char left_data, char right_data) {
		if(root==null)
			return;
		else if(root.data==data) {//들어갈 위치 찾음
			if(left_data!='.') {
				root.left=new Node_Beakjoon_1991(left_data);
			}
			if(right_data!='.') {
				root.right=new Node_Beakjoon_1991(right_data);
			}
		}
		else {
			searchNode(root.left,data,left_data,right_data);
			searchNode(root.right,data,left_data,right_data);
		}
	}
	
	void preOrder(Node_Beakjoon_1991 root) {//로트 왼쪽 오른쪽
		System.out.print(root.data);
		if(root.left!=null) preOrder(root.left);
		if(root.right!=null) preOrder(root.right);
	}
	
	void inOrder(Node_Beakjoon_1991 root) {//왼쪽 루트 오른쪽
		if(root.left!=null) inOrder(root.left);
		System.out.print(root.data);
		if(root.right!=null) inOrder(root.right);
	}
	
	void postOrder(Node_Beakjoon_1991 root) {
		if(root.left!=null) postOrder(root.left);
		if(root.right!=null) postOrder(root.right);
		System.out.print(root.data);
	}
}
public class Beakjoon_1991 {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		N=Integer.parseInt(br.readLine());
		
		Tree tree=new Tree();
		
		for(int i=0;i<N;i++) {
			char[] tmp;
			tmp=br.readLine().replaceAll(" ","").toCharArray();
			tree.createNode(tmp[0],tmp[1],tmp[2]);
		}
		tree.preOrder(tree.root);
		System.out.println();
		
		tree.inOrder(tree.root);
		System.out.println();
		
		tree.postOrder(tree.root);
		System.out.println();
	}

}
