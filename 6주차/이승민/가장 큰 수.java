import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        // int 상태에서 내림차순 정렬? -> 10, 6, 2 -> 1062 -> X
        // String 상태에서 내림차순 정렬? -> 마찬가지
        // 10, 6 -> 106, 6, 10 -> 610 같은 경우를 모두 대응하기 위해 둘의 순서를 바꿔가며 더해서 나온 문자열을 비교해서 내림차순 정렬한다.
        String[] nums = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            nums[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(nums, (s1, s2) -> {
            return (s2 + s1).compareTo(s1 + s2);
        });

        if (nums[0].equals("0")) {
            return "0";
        }

        String ans = "";
        for (String n : nums) {
            ans += n;
        }

        return ans;
    }
}