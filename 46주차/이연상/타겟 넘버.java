// [PRG] 타겟 넘버

class Solution {
    public int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }

    public int dfs(int[] numbers, int target, int sum, int depth) {
        if (depth == numbers.length) {
            return sum == target ? 1 : 0;
        }

        int add = dfs(numbers, target, sum + numbers[depth], depth + 1);
        int subtract = dfs(numbers, target, sum - numbers[depth], depth + 1);

        return add + subtract;
    }
}