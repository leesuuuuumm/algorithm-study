import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int count = 0;			
            int[] ary = new int[a + 1];
            ary[0] = ary[1] = 1;		

            for (int j = 2; j * j <= a; j++) { // 에라토스테네스의 체
                for (int k = j + j; k <= a; k += j) {
                    ary[k] = 1;
                }
            }
            for (int j = 2; j <= a / 2; j++) {	// 두 소수를 더했을 때 a가 나오는 경우 확인
                if (ary[j] == 0 && ary[a - j] == 0) {
                    count++;
                }
            }
            System.out.println(count);
        }
        sc.close();
    }
}
