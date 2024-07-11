// [PRG] 가장 큰 수

import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        
        String[] arr = new String[numbers.length];
        for(int i = 0; i < numbers.length; i++) {
            arr[i] = Integer.toString(numbers[i]);
        }
        
        Arrays.sort(arr, (a, b) -> (b+a).compareTo(a+b));
        if(arr[0].equals("0")) return "0";
        
        String answer = "";
        for(String num : arr) {
            answer += num;
        }
    
        return answer;
    }
}