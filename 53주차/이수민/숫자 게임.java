import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        Deque<Integer> b = new ArrayDeque<>();
        Deque<Integer> a = new ArrayDeque<>();
        
        for(int i=0;i<A.length;i++){
            a.offerFirst(A[i]);
        }
        
        for(int i=0;i<B.length;i++){
            b.offerFirst(B[i]);
        }
        for(int i=0;i<B.length;i++){
            if(b.peek()<=a.peek()){
                b.pollLast();
            }else{
                b.poll();
                answer++;
            }
            a.poll();
        }
        return answer;
    }
}
