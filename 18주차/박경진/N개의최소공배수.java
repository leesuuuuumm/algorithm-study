class Solution {
    public int solution(int[] arr) {
        int answer = arr[0];
        int gcd = arr[0];
        
        for(int i = 1; i < arr.length; i++){
            gcd = findGCD(answer,arr[i]);
            answer = (answer*arr[i]) / gcd;
            System.out.println(gcd);
        }
        
        return answer;
    }
    
    public int findGCD(int x, int y){
        int t = x % y;
        
        if(t == 0) 
            return y;
        else 
            return findGCD(y, t);
    }
}
