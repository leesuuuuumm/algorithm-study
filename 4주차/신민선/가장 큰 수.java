import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String s[]=new String[numbers.length];
        for(int i=0;i<numbers.length;i++){
            s[i]=Integer.toString(numbers[i]);
        }
        Arrays.sort(s,new Comparator<String>(){
            public int compare(String s1, String s2){
                return (s2 + s1).compareTo(s1 + s2);
            }
        });
        String answer = "";
        boolean check=false;
        for(int i=0;i<s.length;i++){
            if(s[i].compareTo("0")!=0){
                check=true;
            }
            answer+=s[i];
        }
        if(check==false)
            return "0";
        return answer;
    }
} 
