// [PRG] 방문 길이

import java.util.*;

class Solution {
    
    static int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상 하 좌 우
    static int[][] map;
    static boolean[][] visit;
    
    public int solution(String dirs) {
        int answer = 0;
        
        map = new int[11][11];
        HashMap<String, Integer> visit = new HashMap<>();
        
        int x = 5;
        int y = 5;
        for(int i = 0; i < dirs.length(); i++) {
            char dir = dirs.charAt(i);
            int dirIdx = 0;
            
            if(dir == 'U') dirIdx = 0;
            if(dir == 'D') dirIdx = 1;
            if(dir == 'L') dirIdx = 2;
            if(dir == 'R') dirIdx = 3;
            
            int nx = x + move[dirIdx][0];
            int ny = y + move[dirIdx][1];
            
            if(nx >= 11 || ny >= 11 || nx < 0 || ny < 0)
                continue;
            
            String isVisit = String.valueOf(x) + String.valueOf(y)
                + String.valueOf(nx) + String.valueOf(ny);
            
            String isVisitReverse = String.valueOf(nx) + String.valueOf(ny)
                + String.valueOf(x) + String.valueOf(y);
            
            if(!visit.containsKey(isVisit)) {
                if(!visit.containsKey(isVisitReverse)) {
                    visit.put(isVisit, 1);
                    visit.put(isVisitReverse, 1);
                    answer++;
                }
            }
            
            x = nx;
            y = ny;
        }
        
        return answer;
    }
}