
class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        //0지우고, 지워진 x값 길이를 이진변환 해라

        while(true){
            if(s.equals("1")) break;
            
            String[] str = s.split("");
            int tmp = s.length();            
            s = s.replaceAll("0","");
            answer[1]+= (tmp-s.length());
            
            s = Integer.toBinaryString(s.length());
            answer[0]++;
        }
        return answer;
    }
}
