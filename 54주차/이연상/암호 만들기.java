// [BOJ] 암호 만들기

import java.util.*;

public class PasswordGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int L = scanner.nextInt();
        int C = scanner.nextInt();
        scanner.nextLine(); 

        String[] alphabet = scanner.nextLine().split(" ");
        Arrays.sort(alphabet);

        generateCombinations(alphabet, new char[L], 0, 0, L);
    }

    private static void generateCombinations(String[] alphabet, char[] current, int index, int start, int L) {
        if (index == L) {
            if (isValid(current)) {
                System.out.println(new String(current));
            }
            return;
        }

        for (int i = start; i < alphabet.length; i++) {
            current[index] = alphabet[i].charAt(0);
            generateCombinations(alphabet, current, index + 1, i + 1, L);
        }
    }

    private static boolean isValid(char[] password) {
        int vowels = 0;
        for (char c : password) {
            if ("aeiou".indexOf(c) >= 0) {
                vowels++;
            }
        }
        int consonants = password.length - vowels;
        return vowels >= 1 && consonants >= 2;
    }
}