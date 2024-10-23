class Solution {
    int ans;
    public int solution(int[] numbers, int target) {
        ans = 0;
        
        dfs(numbers, target, 0,0);
        return ans;
    }
    private void dfs(int[] numbers, int target, int sum, int depth){
        if(depth == numbers.length){
            if(sum == target){
                ans++;
            }
            return;
        }
        
        dfs(numbers, target, sum+numbers[depth],depth+1);
        dfs(numbers, target, sum-numbers[depth],depth+1);
    }
}
