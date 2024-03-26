import java.util.*;

class Solution {
    public String solution(String s) {
        String[] input = s.split(" ");
        int[] converted = new int[input.length];
        
        for (int i = 0; i < input.length; i++) {
            converted[i] = Integer.parseInt(input[i]);
        }
        Arrays.sort(converted);
        return converted[0] + " " + converted[input.length - 1];
    }
}