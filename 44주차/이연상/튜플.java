// [PRG] 튜플

import java.util.*;

class Solution {
    public ArrayList<Integer> solution(String s) {
        
        String[] temp = s.split("\\},\\{");
        
        temp[0] = temp[0].substring(2, temp[0].length());
        temp[temp.length - 1] = temp[temp.length - 1].substring(0, temp[temp.length - 1].length() -2);
        ArrayList<Integer>[] listArr = new ArrayList[temp.length];
        
        for(int i = 0; i < temp.length; i++) {
            String[] c = temp[i].split(",");
            
            ArrayList<Integer> list = new ArrayList<>();
            for(int j = 0; j < c.length; j++) {
                list.add(Integer.parseInt(c[j]));
            }
            listArr[i] = list;
        }
        
        Arrays.sort(listArr, (o1, o2) -> o1.size() - o2.size());
        ArrayList<Integer> answer = new ArrayList<>();
        for(ArrayList<Integer> list : listArr) {
            for(int a : list) {
                if(answer.contains(a)) {
                    continue;
                }
                answer.add(a);
            }
        }
        
        return answer;
    }
}