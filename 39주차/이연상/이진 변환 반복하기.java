// [PRG] 이진 변환 반복하기

class Solution {
    public int[] solution(String s) {
        int a = 0; int b = 0;
        while(s != "1") {
            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == '0') {
                    b += 1;
                }
            }
            s = s.replace("0", "");
            if(s.length() == 1) {
                a += 1;
                break;
            }
            s = Integer.toString(s.length(), 2);
            a += 1;
        }
        
        return new int[]{a, b};
    }
}