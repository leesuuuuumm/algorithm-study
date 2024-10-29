// [PRG] 오픈채팅방

import java.util.*;

class Solution {
    
    public ArrayList<String> solution(String[] record) {
        
        HashMap<String, String> map = new HashMap<>();
        for(String reco : record) {
            String[] temp = reco.split(" ");
            String enter = temp[0];
            String uid = temp[1];
            
            if(enter.equals("Enter") || enter.equals("Change")) {
                map.put(uid, temp[2]);
            }
        }
        
        ArrayList<String> res = new ArrayList<>();
        for(String reco : record) {
            String[] temp = reco.split(" ");
            String enter = temp[0];
            String uid = temp[1];
            
            if(enter.equals("Enter"))
                res.add(map.get(uid) + "님이 들어왔습니다.");
            if(enter.equals("Leave"))
                res.add(map.get(uid) + "님이 나갔습니다.");
        }
        
        return res;
    }
}