class Solution {
    int answer;
    
    public int solution(int[] numbers, int target) {
        answer = 0;
        int l = numbers.length;
        
        answer = dfs(0, 0, target, numbers); //현재 인덱스, 각 경우별 연산결과, 타켓값, 가능 연산 수, 배열의 길이, 배열
        return answer;
    }
    
    public int dfs(int now, int cnt, int target, int[] n){
        //만약 지금 인덱스가 배열을 벗어날 경우
        if(now == n.length){
            if(target == cnt){ //타켓값과 연산결과가 같은 경우
                answer++;
            }
        }
        else {
            dfs(now+1, cnt+n[now], target, n);
            dfs(now+1, cnt-n[now], target, n);
        }
        
        return answer;
    }
}
