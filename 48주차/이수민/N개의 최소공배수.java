import java.util.*;
class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        Arrays.sort(arr);
        a:for(int i=arr[arr.length-1]*2;;i++){
            int cnt=0;
            for(int j=0;j<arr.length;j++){
                if(i%arr[j]==0){
                   cnt++;
                }
                if(cnt==arr.length) {
                    answer = i;
                    break a;
                }
            }
            
        }
        
        return answer;
    }
}
