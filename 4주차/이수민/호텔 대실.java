import java.util.*;
class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        int[][] time = new int[book_time.length][2];
        
        for(int i=0;i<book_time.length;i++){
            for(int j=0;j<2;j++){
                String[] str = book_time[i][j].split(":");
                time[i][j] = Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
                if(j == 1){
                    time[i][j] +=10;
                }
            }
        }
        Arrays.sort(time, (o1,o2) ->{
               
            return Integer.compare(o1[0],o2[0]);
        });
        
        boolean[] v = new boolean[book_time.length];
        
        for(int i=0;i<book_time.length;i++){
            if(!v[i]){
                int t = i;
                v[i] = true;
                for(int j=i+1;j<book_time.length;j++){
                    if(!v[j]){
                        if(time[t][1]<=time[j][0]){
                            v[j] = true;
                            t=j;
                        }
                    }
                }
                answer++;
            }
        }
        return answer;
    }
}

// 문제 잘읽기...
