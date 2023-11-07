import java.util.*;
class Solution {
    public ArrayList<Integer> solution(String s) {
        ArrayList<Integer> answer =new ArrayList<Integer>(); 
        StringBuilder sb = new StringBuilder();

        sb.append(s.substring(2,s.length()-2).replace("},{","-"));

        String[] str = sb.toString().split("-");

      Arrays.sort(str,new Comparator<String>(){
            public int compare(String o1,String o2){
                return Integer.compare(o1.length(),o2.length());
            }
        });
        
        for(int i=0;i<str.length;i++){
            String[] subStr = str[i].split(",");
            
            for(int j=0;j<subStr.length;j++){
                int n =  Integer.parseInt(subStr[j]);
                if(!answer.contains(n)){
                    answer.add(n);
                }
            }
            
        }        
        return answer;
    }
}
