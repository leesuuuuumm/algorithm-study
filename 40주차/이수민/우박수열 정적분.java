import java.util.*;
class Solution {
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];
        
        ArrayList<Integer> list = new ArrayList<>();
        list.add(k);
        
        while(k > 1) {
            if(k % 2 == 0) {
                k /= 2;
            } else {
               k = k * 3 + 1;
            }
            list.add(k);
        }
        
        for(int i = 0; i < ranges.length; i++) {
            int s = ranges[i][0];
            int e = list.size() - 1 + ranges[i][1];
            
            if(s > e) {
                answer[i] = -1;
                continue;
            }
            
            double sum = 0;
            for(int j = s; j < e; j++) {
                double now = list.get(j);
                double target = list.get(j + 1);
                sum += (now + target) / 2;
            }
            answer[i] = sum;
        }
        return answer;
    }
}
