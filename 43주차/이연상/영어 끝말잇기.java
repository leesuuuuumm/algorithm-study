// [PRG] 영어 끝말잇기

import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];

        ArrayList<String> wordList = new ArrayList<>();
        for(int i = 0; i < words.length; i++) {
            if(i == 0) {
                wordList.add(words[i]);
                continue;
            }
            String temp = wordList.get(wordList.size() - 1);
            if(wordList.contains(words[i]) || 
               temp.charAt(temp.length() - 1) != words[i].charAt(0)) {
                if((i + 1) % n == 0) {
                    answer[0] = n;
                    answer[1] = (i + 1) / n;
                }
                else {
                    answer[0] = (i + 1) % n;
                    answer[1] = (i + 1 + n) / n;
                }  
                break;
            }
            wordList.add(words[i]);
        }
        
        return answer;
    }
}