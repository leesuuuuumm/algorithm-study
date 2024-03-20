class Solution {    
    public int[] solution(int n, long left, long right) {       
        int[] answer = new int[(int) (right - left) + 1];
        
        for (int i = 0; i <= (int) (right - left); i++) {
            int r = (int) ((i + left) / n);
            int c = (int) ((i + left) % n);
            
            int max = Math.max(r, c);
            answer[i] = max + 1;
        }
        
        return answer;
    }
}