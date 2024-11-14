// [BOJ] 곱셈

import java.util.Scanner;

public class Multiplication {
    static int c;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        c = scanner.nextInt();

        System.out.println(go(a, b));
    }

    static long go(int a, int b) {
        if (b == 1) {
            return a % c;
        } else {
            long tmp = go(a, b / 2);
            if (b % 2 == 0) {
                return (tmp * tmp) % c;
            } else {
                return (tmp * tmp % c * a) % c;
            }
        }
    }
}