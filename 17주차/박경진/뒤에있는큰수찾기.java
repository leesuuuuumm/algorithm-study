import java.util.*;

class Solution {
    int N;
    
    public int[] solution(int[] numbers) {
        N = numbers.length;
        int[] answer = new int[N];
        
        answer = find(numbers);
        
        return answer;
    }
    
    int[] find(int[] numbers){
        int[] temp = new int[N];
        Stack<Integer> s = new Stack<>();
        
        s.push(0); //0번 인덱스 push
        
        for(int i = 1; i < N; i++){
            //현재 스택의 끝에 저장돼있는 값보다 다음 인덱스 값이 크지 않다면 계속 push
            //크다면 스택이 빌 때까지 pop하면서 뒤에 큰 저장
            while(!s.isEmpty() && (numbers[s.peek()] < numbers[i])){
                temp[s.pop()] = numbers[i]; 
            }
            s.push(i);
        }
        
        while(!s.isEmpty()){
            //뒤에 큰 수가 존재하지 않는 경우
            temp[s.pop()] = -1;
        }
        
        return temp;
    }
}
