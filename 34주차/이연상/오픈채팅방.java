// [PRG] 오픈채팅방

import java.util.*;

class Solution {
    public ArrayList<String> solution(String[] record) {
        ArrayList<String> answer = new ArrayList<>();
        
        HashMap<String, String> map = new HashMap<>();
        for(int i = 0; i < record.length; i++) {
            String[] temp = record[i].split(" ");
            
            if(temp[0].equals("Enter") || temp[0].equals("Change")) {
                map.put(temp[1], temp[2]);
            }
        }
        
        for(int i = 0; i < record.length; i++) {
            String[] temp = record[i].split(" ");
            
            if(temp[0].equals("Enter")) {
                answer.add(map.get(temp[1]) + "님이 들어왔습니다.");
            }
            else if(temp[0].equals("Leave")) {
                answer.add(map.get(temp[1]) + "님이 나갔습니다.");
            }
        }
        
        return answer;
    }
}