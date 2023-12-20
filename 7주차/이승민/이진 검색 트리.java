import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /*

     */
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }

        void insert(int num) {
            if (num < this.data) {
                if (this.left == null) {
                    this.left = new Node(num);
                } else {
                    this.left.insert(num);
                }
            } else {
                if (this.right == null) {
                    this.right = new Node(num);
                } else {
                    this.right.insert(num);
                }
            }
        }
    }

    static void postOrder(Node root) {
        if (root == null) {
            return;
        }

        postOrder(root.left);
        postOrder(root.right);
        sb.append(root.data + "\n");
    }

    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sb = new StringBuilder();
        Node root = new Node(Integer.parseInt(br.readLine()));
        // 전위 순회한 결과를 통해 원래의 트리 구성
        while (true) {
            String input = br.readLine();

            if (input == null || input.isEmpty()) {
                break;
            }

            root.insert(Integer.parseInt(input));
        }

        // 후위 순회 진행 후 결과 출력
        postOrder(root);

        System.out.println(sb);
    }
}
