import java.util.*;

class Solution {
    public long[] solution(long[] nums) {
        // nums[i]를 짝수일 때 홀수일 때로 구분
        // 짝수: 끝자리가 0으로 끝난다. -> +1만 해주면 된다.
        // 홀수: 끝자리가 1로 끝난다.
        // 홀수는 전부 1로 이루어진 케이스, 1과 0이 섞인 케이스를 나눈다.
        // 전부 1인 케이스 -> 1인 자릿수가 하나 더 늘어나고, 그 중 가장 작은 수가 필요하다 -> 맨 앞 1을 10으로 바꾼다.
        // 1과 0이 섞인 케이스 -> 끝에서부터 봤을 때 처음 0이 나오는 곳을 1로 바꾸고 그 다음 1을 0으로 바꾼다.
        long[] ans = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                ans[i] = nums[i] + 1;
            } else {
                String s = Long.toString(nums[i], 2);
                int zeroIdx = s.lastIndexOf("0");
                if (zeroIdx != -1) { // 섞인 케이스
                    String tmp = s.substring(0, zeroIdx) + "10" + s.substring(zeroIdx + 2, s.length());
                    ans[i] = Long.parseLong(tmp, 2);
                } else { // 전부 1인 케이스
                    ans[i] = Long.parseLong("10" + s.substring(1, s.length()), 2);
                }
            }
        }

        return ans;
    }
}