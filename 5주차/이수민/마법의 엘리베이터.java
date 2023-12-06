import java.util.*;
class Solution {
    public int solution(int storey) {
        String[] str = Integer.toString(storey).split("");
        int[] arr = new int[str.length];
        for(int i=str.length-1;i>=0; i--){
            arr[str.length-1-i] = Integer.parseInt(str[i]);
            
        }
        int cnt = 0;
        for(int i=0;i<arr.length-1;i++){
            if(arr[i]<=5){
                if(arr[i]==5){
                    if(arr[i+1]>=5){
                        arr[i+1]+=1;    
                    }
                }
                cnt+=arr[i];
                
            }else{
                cnt+= 10 -arr[i];
                arr[i+1]+=1;
            }
        }
        
        if(arr[arr.length-1] <=5){
                cnt+=arr[arr.length-1];
            }else{
                cnt+= 10 -arr[arr.length-1];
            cnt+=1;
            }
        
        return cnt;
    }
}
