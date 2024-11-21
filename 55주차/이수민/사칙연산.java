import java.util.*;
import java.io.*;
 
public class Solution {
    static class Node {
        int val; 
        String op; 
        int nodeL;
        int nodeR;
 
    }
 
    static ArrayList<Node> node;
 
    static double result;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int t = 1; t <= 10; t++) {
            int N = Integer.parseInt(br.readLine());
            node = new ArrayList<>();
 
            for (int i = 0; i <= N; i++) {
                node.add(new Node());
            }
 
            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken());
                String op = st.nextToken();
                if (op.matches("[\\+\\-\\/\\*]")) {
                    node.get(idx).op = op;
                    node.get(idx).nodeL = Integer.parseInt(st.nextToken());
                    node.get(idx).nodeR = Integer.parseInt(st.nextToken());
                } else {
                    node.get(idx).val = Integer.parseInt(op);
                }
 
            }
             
 
            postOrder(1);
            System.out.println("#"+t+" "+(int) result);
 
        }
 
    }
 
    private static double postOrder(int cur) {
        String op = node.get(cur).op;
 
        if (op != null) {
            switch (op) {
            case "+":
                result = postOrder(node.get(cur).nodeL) + postOrder(node.get(cur).nodeR);
                break;
            case "-":
                result = postOrder(node.get(cur).nodeL) - postOrder(node.get(cur).nodeR);
                break;
            case "*":
                result = postOrder(node.get(cur).nodeL) * postOrder(node.get(cur).nodeR);
                break;
            case "/":
                result = postOrder(node.get(cur).nodeL) / postOrder(node.get(cur).nodeR);
                break;
            }
        } else {
            result = node.get(cur).val;
        }
 
        return result;
    }
}
