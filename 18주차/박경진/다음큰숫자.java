class Solution {
    public int solution(int n) {
        int answer = 0;
        
        String s = Integer.toBinaryString(n);
        int cnt = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '1') cnt++;
        }
        
        for(int j = n + 1; j < Integer.MAX_VALUE; j++){
            s = Integer.toBinaryString(j);
            int temp = 0;
            
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == '1') temp++;
            }
            
            if(temp == cnt) {
                answer = j;
                break;
            }
        }
        return answer;
    }
}
