
import java.io.*;
import java.util.*;

class k진수에서소수개수구하기 {
    public int solution(int n, int k) {

        int answer = 0;
        String str = Integer.toString(n, k);
        System.out.println(str);

        String[] array = str.split("0");

        for (String s :array) {
            if (s.equals("")) continue;
            System.out.println(s);
            long num = Long.parseLong(s);
            if (isPrime(num)) {
                answer++;
            }
        }

        return answer;
    }

    static boolean isPrime(long num) {
        if (num < 2) return false;

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % 2 == 0) {
                return false;
            }
        }
        return true;
    }
}
