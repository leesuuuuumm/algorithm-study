import java.util.*;
class Solution {
    static ArrayList<String> list;
    static boolean[] v;
    public String[] solution(String[][] tickets) {
        v = new boolean[tickets.length];
        list = new ArrayList<>();
        dfs(0,"ICN","ICN", tickets);
        
        Collections.sort(list);
        String[] str = list.get(0).split(" ");
        String[] answer = new String[str.length];
        for(int i=0;i<str.length;i++){
            answer[i] = str[i];
        }
    
        
        
        return answer;
    }
    static void dfs(int idx, String start, String route, String[][] tickets){
        if(idx == tickets.length){
            list.add(route);
            return;
        }
        for(int i=0;i<tickets.length;i++){
            if(tickets[i][0].equals(start)&& !v[i]){
                v[i] = true;
                dfs(idx+1, tickets[i][1], route+" "+tickets[i][1], tickets);
                v[i] = false;
                
            }
        }
    }
}
