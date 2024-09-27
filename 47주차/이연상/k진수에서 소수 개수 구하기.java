// [PRG] k진수에서 소수 개수 구하기

import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        String st = Integer.toString(n, k);
        String temp = "";
        for(int i = 0; i < st.length(); i++) {
            long num = (long) (st.charAt(i) - '0');
            
            if(num == 0) {
                if(temp.length() != 0) {
                    if(isPrime(Long.parseLong(temp)))
                        answer++;
                }
                temp = "";
                continue;
            }
            
            temp += st.charAt(i) - '0';
        }
        
        if (temp.length() != 0) {
            if(isPrime(Long.parseLong(temp))) answer += 1;
        }
        
        return answer;
    }
    
    public boolean isPrime(long num) {
        if(num <= 1) return false;
        for(int i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}