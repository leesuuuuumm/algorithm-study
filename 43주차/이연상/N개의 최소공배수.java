// [PRG] N개의 최소공배수

class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        
        boolean res = false;
        int i = 1;
        while(!res) {
            res = true;
            for(int j = 0; j < arr.length; j++) {
                if(i % arr[j] != 0) {
                    res = false;
                    break;
                }
            }
            if(res) {
                answer = i;
                break;
            }
            i++;
        }
        
        return answer;
    }
}