import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int[] inorder, postorder;
    static Map<Integer, Integer> inorderPos;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        inorder = new int[N];
        postorder = new int[N];
        inorderPos = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
            inorderPos.put(inorder[i], i);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }

        preorder(0, N - 1, 0, N - 1);

        System.out.println(sb);
    }

    static StringBuilder sb = new StringBuilder();

    private static void preorder(int ins, int ine, int pos, int poe) {
        if (ins > ine || pos > poe) return;

        int root = postorder[poe];

        sb.append(root).append(" ");


        int lcount = inorderPos.get(root) - ins;

        // 왼쪽 정점 탐색
        preorder(ins, ins + lcount - 1, pos, pos + lcount - 1);
        // 오른쪽 정점 탐색
        preorder(ins + lcount + 1, ine, pos + lcount, poe - 1);
    }
}