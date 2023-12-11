import java.util.*;
class Solution {
    public int solution(String word) {
        int answer = 0;
        int[] value = {781, 156, 31, 6, 1};
        String[] str = word.split("");
        String s = "AEIOU";
        for(int i=0;i<str.length;i++){
            if(s.indexOf(str[i])!=-1){
                int idx = s.indexOf(str[i]);
                answer+=(value[i]*idx);
            }
        }
        return answer+str.length;
    }
}
