import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        int N = new Scanner(System.in).nextInt();

        BigDecimal result = BigDecimal.ONE;

        for (int i = 0; i < N / 3; i++) {
            result = result.multiply(new BigDecimal(3));
        }

        if (N % 3 == 1) {
            result = result.divide(new BigDecimal(3), 0).multiply(new BigDecimal(4));
        } else if (N % 3 == 2) {
            result = result.multiply(new BigDecimal(2));
        }

        System.out.println(N <= 4 ? N : result.toBigInteger().mod(new BigDecimal(10007).toBigInteger()));
    }
}