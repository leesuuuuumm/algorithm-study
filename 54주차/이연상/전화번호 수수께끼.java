// [BOJ] 전화번호 수수께끼

import java.util.*;

public class PhoneNumberPuzzle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 1; i <= t; i++) {
            String s = scanner.nextLine();

            Map<Character, Integer> m = Map.of(
                'Z', 0, 'G', 8, 'X', 6, 'W', 2, 'U', 4, 
                'F', 5, 'H', 3, 'I', 9, 'V', 7, 'O', 1
            );

            int[] c = new int[10];
            for (char key : m.keySet()) {
                c[m.get(key)] = countChar(s, key);
            }

            c[3] -= c[8];
            c[5] -= c[4];
            c[7] -= c[5];
            c[9] -= c[5] + c[6] + c[8];
            c[1] -= c[0] + c[2] + c[4];

            StringBuilder result = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                result.append(String.valueOf(j).repeat(c[j]));
            }
            System.out.println("Case #" + i + ": " + result);
        }
    }

    private static int countChar(String s, char ch) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == ch) {
                count++;
            }
        }
        return count;
    }
}