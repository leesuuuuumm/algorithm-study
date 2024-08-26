class Solution {
    public int solution(int n) {
        int answer = 1;
        int s = 1;
        int e =2;
        
        int sum = 1;
        
        while(true){
            if(e>=n || s == e) break;
            
            if(sum+e<n){
                sum+= e++;
            }else if(sum+e == n){
                answer++;
                sum+=e;
                e++;
                sum-=s;
                s++;
            }else if(sum+e>n){
                sum-=s;
                s++;
            }
        }
        return answer;
    }
}
