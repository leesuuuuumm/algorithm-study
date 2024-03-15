import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        Stack<int[]> stack = new Stack<>();

        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1);
        
        for (int i = 0; i < numbers.length; i++) {
            // 스택이 비어있으면 스택을 채워준다.
            if (stack.isEmpty()) {
                stack.push(new int[]{numbers[i], i});
                continue;
            }
            
            // 스택에 있는 값이 현재 인덱스의 값보다 작으면 스택에 있는 값을 answer 배열에 넣자
            while (!stack.isEmpty() && stack.peek()[0] < numbers[i]) {
                int[] v = stack.pop();
                
                answer[v[1]] = numbers[i];
            }
            
            stack.push(new int[]{numbers[i], i});
        }
            
        
        return answer;
    }
}