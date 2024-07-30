// [PRG] JadenCase 문자열 만들기

class Solution {
    public String solution(String s) {
        String answer = "";

        String[] sList = s.split(" ");

        for(String temp : sList)
            if(temp.length() > 0)
                answer += temp.substring(0,1).toUpperCase() + temp.substring(1).toLowerCase() + " ";
            else
                answer += " ";
        if (s.charAt(s.length() - 1) != ' ')
            answer = answer.substring(0, answer.length()-1);
        return answer;
    }
}