// [BOJ] 4와 7

import java.util.Scanner;

public class FourAndSeven {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        StringBuilder result = new StringBuilder();

        while (N > 0) {
            int m = N % 2;
            N = N / 2;
            if (m == 0) {
                N -= 1;
                result.insert(0, '7');
            } else { // 홀수면
                result.insert(0, '4');
            }
        }

        System.out.println(result);
    }
}