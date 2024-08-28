// [PRG] 연속 부분  수열  합의 개수

import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int[] list = new int[elements.length * 2];
        for(int i = 0; i < list.length; i++) {
            list[i] = elements[i % elements.length];
        }
        
        HashSet<Integer> set = new HashSet<>();
        
        for(int i = 0; i < elements.length; i++) {
            for(int j = 0; j < elements.length; j++) {
                set.add(Arrays.stream(list, j, j+i).sum());
            }
        }
        
        return set.size();
    }
}