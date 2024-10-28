// [PRG] 주차요금계산

import java.util.HashMap;
import java.util.ArrayList;

class Solution {
    
    static int basicTime;
    static int basicFee;
    static int unitTime;
    static int unitFee;
    
    public ArrayList<Integer> solution(int[] fees, String[] records) {
        
        basicTime = fees[0];
        basicFee = fees[1];
        unitTime = fees[2];
        unitFee = fees[3];
        
        HashMap<Integer, String> map = new HashMap<>();
        HashMap<Integer, Integer> answer = new HashMap<>();

        for(String record : records) {
            String[] recordArr = record.split(" ");
            int car = Integer.valueOf(recordArr[1]);
            String time = recordArr[0];
            String inOut = recordArr[2];
            
            if(inOut.equals("IN")) {
                map.put(car, time);
            }
            if(inOut.equals("OUT")) {
                String[] inTime = map.get(car).split(":");
                String[] outTime = time.split(":");
                int hour = Integer.valueOf(outTime[0]) - Integer.valueOf(inTime[0]);
                int minuit = Integer.valueOf(outTime[1]) - Integer.valueOf(inTime[1]);
                
                if(minuit < 0) {
                    hour -= 1;
                    minuit = 60 + minuit;
                }
                
                int fee = basicFee + 
                    ((((hour * 60) + minuit) - basicTime) / unitTime) * unitFee;
                
                answer.put(car, answer.getOrDefault(car, 0) + fee);
                map.put(car, "00:00");
            }
        }
        
        ArrayList<Integer> keys = new ArrayList<>(answer.keySet());
        keys.sort((o1, o2) -> answer.get(o1) - answer.get(o2));
        ArrayList<Integer> ans = new ArrayList<>();
        
        for(int key : keys) {
            ans.add(answer.get(key));
        }
        
        return ans;
    }
}