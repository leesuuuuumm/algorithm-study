import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    /*
    -1이면 루트 노드

    dfs를 돌면서 자식의 갯수가 0인 경우에 갯수를 1씩 늘리면 리프 노드의 갯수를 구할 수 있다.
     */
    static int n, root, remove, leaf;
    static ArrayList<Integer>[] child;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        child = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            child[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int par = Integer.parseInt(st.nextToken());
            if (par == -1) {
                root = i;
                continue;
            }
            child[par].add(i);
        }
        remove = Integer.parseInt(br.readLine());
//        vis[remove] = true; // 지울 노드를 이미 방문한 것으로 표시, 따라서 해당 노드의 자식은 방문할 수 없게 된다.

        for (int i = 0; i < n; i++) {
            if (child[i].contains(remove)) {
                child[i].remove(child[i].indexOf(remove));
            }
        }

        if (root != remove) {
            dfs(root);
        }

        System.out.println(leaf);
    }

    static void dfs(int node) {
        if (child[node].size() == 0) {
            leaf++;
            return;
        }

        for (int child : child[node]) {
            dfs(child);
        }
    }
}
