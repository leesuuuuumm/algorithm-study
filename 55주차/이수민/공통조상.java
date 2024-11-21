import java.io.*;
import java.util.*;
 
public class Solution {
    static class Tree {
        int parentNode;
        int childNodeL;
        int childNodeR;
 
    }
 
    static ArrayList<Tree> tree;
    static int cnt;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
 
            int ansParent = 1;
            tree = new ArrayList<>();
 
            boolean[] v = new boolean[V + 1];
 
            for (int i = 0; i <= V; i++) {
                tree.add(new Tree());
            }
            st = new StringTokenizer(br.readLine());
 
            for (int i = 0; i < E; i++) {
                int p = Integer.parseInt(st.nextToken());
                if (tree.get(p).childNodeL == 0) {
                    tree.get(p).childNodeL = Integer.parseInt(st.nextToken());
 
                    tree.get(tree.get(p).childNodeL).parentNode = p;
 
                } else {
                    tree.get(p).childNodeR = Integer.parseInt(st.nextToken());
                    tree.get(tree.get(p).childNodeR).parentNode = p;
                }
 
            }
            int m1 = node1;
            int m2 = node2;
 
            while (true) {
                if (m1 != 0) {
 
                    int p1 = tree.get(m1).parentNode;
 
                    if (v[p1]) {
                        ansParent = p1;
                        break;
 
                    } else {
                        v[p1] = true;
                        m1 = p1;
                    }
                }
                if (m2 != 0) {
                    int p2 = tree.get(m2).parentNode;
                    
                    if (v[p2]) {
                        ansParent = p2;
                        break;
                    } else {
                        v[p2] = true;
                        m2 = p2;
                    }
                }
            }
            cnt = 0;
            
            dfs(ansParent);
            System.out.println("#" + t + " " + ansParent + " " + cnt);
        }
 
    }
 
    private static void dfs(int m) {
        cnt++;
        if(tree.get(m).childNodeL != 0) dfs(tree.get(m).childNodeL);
        if(tree.get(m).childNodeR != 0) dfs(tree.get(m).childNodeR);
 
    }
 
}
