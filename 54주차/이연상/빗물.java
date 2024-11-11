// [BOJ] 빗물

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int[] nums = new int[w];

        for (int i = 0; i < w; i++) {
            nums[i] = sc.nextInt();
        }

        int[][] graph = new int[500][500];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i]; j++) {
                graph[j][i] = 1;
            }
        }

        int res = 0;
        for (int y = 0; y < 500; y++) {
            boolean temp = false;
            int val = 0;
            for (int x = 0; x < 500; x++) {

                if (graph[y][x] == 1) { // 1 만났을 때
                    if (temp) {
                        if (val != 0) {
                            res += val;
                            val = 0;
                        }
                    } else {
                        temp = true;
                    }
                } else { // 0 만났을 때
                    if (temp) {
                        val++;
                    }
                }
            }
        }

        System.out.println(res);
        sc.close();
    }
}