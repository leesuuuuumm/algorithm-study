// [BOJ] 암호 만들기

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int L = sc.nextInt(); // 암호의 길이
        int C = sc.nextInt(); // 알파벳 개수
        sc.nextLine(); // 개행 문자 제거

        String[] alphabet = sc.nextLine().split(" ");
        Arrays.sort(alphabet); // 알파벳을 사전 순으로 정렬

        // 조합을 만들기 위해 helper 함수 호출
        combination(alphabet, new String[L], 0, 0, L);
    }

    // 조합을 생성하는 함수
    public static void combination(String[] alphabet, String[] current, int index, int start, int L) {
        if (index == L) {
            int vowels = 0;
            for (String s : current) {
                if (isVowel(s)) vowels++;
            }

            if (vowels >= 1 && vowels <= L - 2) {
                System.out.println(String.join("", current));
            }
            return;
        }

        for (int i = start; i < alphabet.length; i++) {
            current[index] = alphabet[i];
            combination(alphabet, current, index + 1, i + 1, L);
        }
    }

    // 모음인지 확인하는 함수
    public static boolean isVowel(String s) {
        return "aeiou".contains(s);
    }
}