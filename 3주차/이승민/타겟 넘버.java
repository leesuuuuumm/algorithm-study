class Solution {
    int ans;

    public int solution(int[] numbers, int target) {
        ans = 0;

        dfs(numbers, target, 0, 0);

        return ans;
    }

    public void dfs(int[] numbers, int target, int idx, int sum) {
        if (idx >= numbers.length) {
            if (sum == target) {
                ans++;
            }
            return;
        }

        dfs(numbers, target, idx + 1, sum + numbers[idx]);
        dfs(numbers, target, idx + 1, sum - numbers[idx]);
    }
}