// [BOJ] 전화번호 수수께끼

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int t = sc.nextInt();  // 테스트 케이스 수
        sc.nextLine();  // 개행 문자 제거

        for (int i = 1; i <= t; i++) {
            String s = sc.nextLine();
            Map<Character, Integer> m = new HashMap<>();
            m.put('Z', 0);
            m.put('G', 8);
            m.put('X', 6);
            m.put('W', 2);
            m.put('U', 4);
            m.put('F', 5);
            m.put('H', 3);
            m.put('I', 9);
            m.put('V', 7);
            m.put('O', 1);

            int[] c = new int[10];  // 각 숫자의 개수를 저장하는 배열
            for (char key : m.keySet()) {
                c[m.get(key)] = countChar(s, key);  // 해당 문자의 개수 세기
            }

            // 조건에 따라 숫자의 개수를 조정
            c[3] -= c[8];
            c[5] -= c[4];
            c[7] -= c[5];
            c[9] -= c[5] + c[6] + c[8];
            c[1] -= c[0] + c[2] + c[4];

            // 결과 출력
            StringBuilder result = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                result.append(String.valueOf(j).repeat(c[j]));
            }

            System.out.println("Case #" + i + ": " + result.toString());
        }
    }

    // 문자열에서 특정 문자의 개수를 세는 함수
    public static int countChar(String s, char ch) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == ch) {
                count++;
            }
        }
        return count;
    }
}