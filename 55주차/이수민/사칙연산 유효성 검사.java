import java.util.*;
import java.io.*;
 
public class Solution {
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int t = 1; t <= 10; t++) {
            int N = Integer.parseInt(br.readLine());
            String[] tree = new String[N + 1];
            for (int i = 1; i < N + 1; i++) {
                String[] str = br.readLine().split(" ");
                tree[Integer.parseInt(str[0])] = str[1];
            }
            System.out.println("#" + t + " " + check(tree, N));
        }
    }
 
    private static int check(String[] tree, int N) {
        for (int i = 1; i <= N; i++) {
            if (tree[i].matches("[\\-\\+\\*\\/]")) {
                if (i * 2 >= N) {
                    return 0;
                }
            } else {
                if (i * 2 < N) {
                    return 0;
                }
            }
 
        }
        return 1;
    }
}
