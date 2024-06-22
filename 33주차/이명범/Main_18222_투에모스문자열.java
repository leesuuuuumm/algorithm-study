import java.util.Scanner;

public class Main_18222_투에모스문자열 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long k = sc.nextLong() - 1;

        int count = 0;

        while (k != 0) {
            int log = (int) (Math.log(k) / Math.log(2));

            long dv = (long) Math.pow(2, log);

            k -= dv;

            count++;
        }

        System.out.println(count % 2 == 0 ? 0 : 1);
    }
}