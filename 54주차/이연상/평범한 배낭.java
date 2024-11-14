// [BOJ] 평범한 배낭

import java.util.Scanner;

public class Knapsack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();  
        int k = scanner.nextInt();  

        int[][] item = new int[n + 1][2];
        int[][] d = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            item[i][0] = scanner.nextInt();  
            item[i][1] = scanner.nextInt(); 
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                int w = item[i][0];
                int v = item[i][1];

                if (j < w) {
                    d[i][j] = d[i - 1][j];
                } else {
                    d[i][j] = Math.max(d[i - 1][j], d[i - 1][j - w] + v);
                }
            }
        }

        System.out.println(d[n][k]);
    }
}