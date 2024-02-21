import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int left = 0;
        int right = 0;
        int sum = 0;
        int count = Integer.MAX_VALUE;
        
        while (right < sequence.length && (left <= right)) {
            if (left == right) {
                sum = sequence[left];
            }
            
            if (sum == k) {
                if (count > right - left + 1) { //지금 길이가 기존 길이보다 짧을 경우
                    count = right - left + 1;
                    answer[0] = left;
                    answer[1] = right;
                }
                
                
                
                sum -= sequence[left]; 
                
                if (right < sequence.length - 1) {
                    sum += sequence[right + 1];
                }
                
                if (left == right) {
                    break;
                }
                
                left++;
                right++;

            } 
            else if (sum > k) {
                sum -= sequence[left++];
            } 
            else if (sum < k) {
                if (right < sequence.length - 1) {
                    sum += sequence[right + 1];
                }
                right++;
            }

        }
        return answer;
    }
}
