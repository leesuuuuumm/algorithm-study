import java.util.*;
class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        

        for(int i=0;i<str1.length();i++){
            if(i+2> str1.length())  break;
            String s = str1.substring(i,i+2);
            if(s.matches("[a-z]+")){
                list1.add(s);
            }
        }
             

        for(int i=0;i<str2.length();i++){
            if(i+2> str2.length()) break;
            String s = str2.substring(i,i+2);
            if(s.matches("[a-z]+")){
                list2.add(s);
            }
        }
       int same = 0; 
        for(int i=0;i<list1.size();i++){
            for(int j=0;j<list2.size();j++){
                if(list1.get(i).equals(list2.get(j))){ 
                    list2.remove(j);
                    same++;
                    break;
                }
            }
        }
        
        int sum = (list1.size()+list2.size()) ;
        
        if(sum == 0 && same == 0){
            answer = 65536;
        }else{
            answer = (int)(((double)same/(double)sum) * 65536.0);
        }
        return answer;
    }
}
