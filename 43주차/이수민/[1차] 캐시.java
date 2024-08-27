import java.util.*;
class Solution {
  
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        ArrayList<String> list = new ArrayList<>();
        if(cacheSize == 0 ){
            return cities.length *5;
        }
        
        for(int i=0;i<cities.length;i++){
            String s = cities[i].toLowerCase();
            if(list.size() == cacheSize){
               if(list.contains(s)){
                   list.remove(s);
                   list.add(s);
                   answer+=1;
                   continue;
               }else{
                   list.remove(0);
               }
            }else{
                if(list.contains(s)){
                    list.remove(s);
                    answer+=1;
                    list.add(s);
                    continue;
                }
            }
            list.add(s);
            answer+=5;
        }

        return answer;
    }
}
