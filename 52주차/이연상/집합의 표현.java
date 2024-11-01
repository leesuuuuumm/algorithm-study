// [BOJ] 집합의 표현

import java.util.Scanner;

public class Main {
    static int[] parent;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            int op = scanner.nextInt();
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            if (op == 1) {
                if (find(a) == find(b)) {
                    System.out.println("yes");
                } else {
                    System.out.println("no");
                }
            } else {
                union(a, b);
            }
        }
    }

    static int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]); // 경로 압축
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            if (rootA > rootB) {
                parent[rootA] = rootB;
            } else {
                parent[rootB] = rootA;
            }
        }
    }
}