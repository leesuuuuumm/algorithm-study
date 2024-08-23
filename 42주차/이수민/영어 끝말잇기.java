import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        int num = 0;
        int turn = 1;
        HashSet<String> set = new HashSet<>();
        set.add(words[0]);
        String s = words[0].substring(words[0].length()-1,words[0].length());
        for(int i=1;i<words.length;i++){
             if(i%n ==0){
                turn++;
                 
            }

            if(words[i].substring(0,1).equals(s)&& !set.contains(words[i])){
                s = words[i].substring(words[i].length()-1,words[i].length()); 
                set.add(words[i]);
            }
            else{
                num= (i%n)+1;
                break;
            }
           
            
        }
        if(num == 0) {
            turn = 0;
        }

        answer[0] = num;
        answer[1] = turn;
       return answer;
    }
}
