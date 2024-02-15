class Solution
{
    public int solution(String st)
    {
        int max = 0;
        String[] str = st.split("");

        //홀수인 경우
        for(int i=0;i<str.length;i++){
            int s = i-1;
            int e = i+1;
            int cnt = 0;
            while(true){
                if(!check(s,e,str.length)) break;
                if(!str[s].equals(str[e])) break;
                cnt++;
                s--;
                e++;
            }
            max = Math.max(max, cnt*2+1);
        }
        
        // 짝수인 경우
        
        for(int i=0;i<str.length-1;i++){
            int s = i;
            int e = i+1;
            int cnt = 0;
            while(true){
                if(!check(s,e,str.length)) break;
                if(!str[s].equals(str[e])) break;
                cnt++;
                s--;
                e++;
            }
            max = Math.max(max,cnt*2);
        }
        return max;
    }
    private boolean check(int s, int e, int len){
        return s>=0 && e<len;
    }
}
