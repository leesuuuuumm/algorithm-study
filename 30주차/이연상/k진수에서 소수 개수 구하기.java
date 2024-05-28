// [PRG] k진수에서 소수 개수 구하기

import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String s = Integer.toString(n, k);
        String temp = "";

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '0') {
                if (temp.length() != 0) {
                    if(isPrime(Long.parseLong(temp))) answer += 1;
                    temp = "";
                }
                continue;
            }
            temp += s.charAt(i);
        }
        
        if (temp.length() != 0) {
            if(isPrime(Long.parseLong(temp))) answer += 1;
        }
        
        return answer;
    }
    
    public boolean isPrime(long n) {
        if (n <= 1) return false;
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) return false;
        }
        return true;
    }
}