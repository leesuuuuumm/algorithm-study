import java.util.*;
class Solution
{
    public int solution(String st)
    {
        int max = 0;
        String[] str = st.split("");

        for(int i=0;i<str.length;i++){
            int s = i-1;
            int e = i+1;
            int cnt = 0;

            while(true){
                if(s<0 || e>=str.length) break;
                if(!str[s].equals(str[e])) break;
                s--;
                e++;
                
                cnt++;
            }
            max = Math.max(max, cnt*2+1);
        }
        
        
        for(int i=0;i<str.length-1;i++){
            int s = i;
            int e = i+1;
            int cnt = 0;
            while(true){
                if(s<0 || e>=str.length) break;
                if(!str[s].equals(str[e])) break;
                s--;
                e++;
                cnt++;
            }
            max = Math.max(max,cnt*2);
        }
        
        return max;
    }
}
