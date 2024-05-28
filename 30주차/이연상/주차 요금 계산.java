// [PRG] 주차 요금 계산

import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        HashMap<String, String> map = new HashMap();
        HashMap<String, Integer> res = new HashMap();
        
        for(int i = 0; i < records.length; i++) {
            String[] s = records[i].split(" ");
            if(map.containsKey(s[1])) {
                String[] a = map.get(s[1]).split(":");
                String[] b = s[0].split(":");
                int h1 = (Integer.parseInt(a[0])) * 60 + Integer.parseInt(a[1]);
                int h2 = Integer.parseInt(b[0]) * 60 + Integer.parseInt(b[1]);
                
                res.put(s[1], res.getOrDefault(s[1], 0) + h2 - h1);
                map.remove(s[1]);
            }
            else {
                map.put(s[1], s[0]);
            }
        }
        
        int out = 23 * 60 + 59;
        for (String key : map.keySet()) {
            String[] temp = map.get(key).split(":");
            int h = out - (Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]));
            res.put(key, res.getOrDefault(key, 0) + h);
        }
        
        ArrayList<String> cars = new ArrayList<>();
        for (String key : res.keySet()) {
            cars.add(key);
            double h = Math.ceil( (double) (res.get(key) - fees[0]) / fees[2]);
            if(h <= 0) {
                res.put(key, fees[1]);
            } 
            else {
                res.put(key, fees[1] + (int) h * fees[3]);
            }
        }
        
        int[] answer = new int[cars.size()];
        Collections.sort(cars);
        for(int i = 0; i < cars.size(); i++) {
            answer[i] = res.get(cars.get(i));
        }
    
        return answer;
    }
}