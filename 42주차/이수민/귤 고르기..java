import java.util.*;
class Solution {
   
    final int lastTime = (23*60)+59; 
    public int[] solution(int[] fees, String[] records) {
        
        TreeMap<String, Integer> tm = new TreeMap<>();
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0;i<records.length;i++){
        String[] str = records[i].split(" ");

            String num = str[1];
            String[] t = str[0].split(":");
            int time = (Integer.parseInt(t[0]) *60) + Integer.parseInt(t[1]);
            
            if(str[2].equals("IN") && !map.containsKey(num)){
                map.put(num, time);
            }else if(str[2].equals("OUT")){
                int parkingTime = time - map.get(num);
                tm.put(num, tm.getOrDefault(num,0)+parkingTime);
                map.remove(num);
            }
        }
        
        for(String i: map.keySet()){
            
            String num = i;
            int parkingTime = lastTime - map.get(num);
              
            tm.put(num,tm.getOrDefault(num,0)+parkingTime);
        }
        int[] answer = new int[tm.size()];

        int k = 0;
        for(String i:tm.keySet()){
            int time = tm.get(i);
            int money = fees[1];
            if(time>fees[0]){
                if((time-fees[0])%fees[2] !=0){
                    money += (int) (Math.ceil((double)(time - fees[0])/fees[2])) *fees[3];
                }else
                    money += ((time - fees[0])/fees[2]) *fees[3];
            }
            answer[k++] = money;
        }
        
        return answer;
    }
}
