/*
SWEA 1248
날짜 2023.11.22
*/
import java.util.*;
import java.io.*;


class Node{
    int num;
    Node left;
    Node right;
    Node parent;
     
    Node(int num){
        this.num=num;
        left=null;
        right=null;
        parent=null;
    }
}
 
class BinaryTree{
    private static int MAX_NODE=10001;
    public Node node[]=new Node[MAX_NODE];//main에서 접근해야함
     
    Node new_node(int num) {
        node[num]=new Node(num);
        return node[num];
    }
     
    void insert(int p, int c) {
        if(node[p]==null) {
            node[p]=new_node(p);
        }
        if(node[c]==null) {
            node[c]=new_node(c);
        }
        if(node[p].left==null) {
            node[p].left=node[c];
        }
        else {
            node[p].right=node[c];
        }
        node[c].parent=node[p];
    }
     
    int LCA(int a, int b) {
        int check[]=new int[MAX_NODE];//디폴트 0으로 초기화 되어있음
        Queue<Integer> q_a=new LinkedList<>();
        Queue<Integer> q_b=new LinkedList<>();
        int index_a;
        int index_b=0;
        q_a.add(a);
        q_b.add(b);
        while(!q_a.isEmpty()) {
            index_a=q_a.poll();
            check[index_a]=1;//정점 방문 체크
            if(node[index_a].parent!=null) {
                q_a.add(node[index_a].parent.num);
            }   
        }
        while(!q_b.isEmpty()) {
            index_b=q_b.poll();
            if(check[index_b]==1) {//최소 공통 조상 찾음
                break;
            }
            if(node[index_b].parent!=null) {
                q_b.add(node[index_b].parent.num);
            }
        }
        return index_b;
    }
    int size(int a) {
        Queue <Integer>q=new LinkedList<>();
        int size=0;
        int index;
        q.add(a);
        while(!q.isEmpty()) {
            index=q.poll();
            size++;
            if(node[index].left!=null) {
                q.add(node[index].left.num);
            }
            if(node[index].right!=null) {
                q.add(node[index].right.num);
            }
        }
        return size;
    }
}
 
class SWEA_1248
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
         
        int V;//정점의 갯수
        int E;//간선의 갯수
        int a;
        int b;
        int p;//부모
        int c;//자식
        for(int test_case = 1; test_case <= T; test_case++)
        {
            BinaryTree tree=new BinaryTree();
            V=sc.nextInt();
            E=sc.nextInt();
            a=sc.nextInt();
            b=sc.nextInt();
            for(int i=0;i<E;i++) {
                p=sc.nextInt();
                c=sc.nextInt();
                tree.insert(p, c);
            }
            int root=tree.LCA(a,b);
            System.out.printf("#%d %d %d\n",test_case, root, tree.size(root));
        }
    }
}
