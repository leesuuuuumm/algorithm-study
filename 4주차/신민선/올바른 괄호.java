class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int number=0;
        //첫 시작은 반드시 ( 으로 들어와야함. if문 (:0, ):1
        // ( 가 들어오면 number++
        // ) 가 들어오면 number가 1개 이상이면 --, 아닌경우 answer false처리 후 break
        // for문이 끝나고 number가 0이 아니면 false
        if(s.charAt(0)=='('){
            number++;
            for(int i=1;i<s.length();i++){
                if(s.charAt(i)=='(')
                    number++;
                else{
                    if(number>0){
                        number--;
                    }
                    else{
                        answer=false;
                        break;
                    }
                }
            }
        }
        else{
            answer=false;
        }
        if(number!=0){
            answer=false;
        }
        return answer;
    }
}
