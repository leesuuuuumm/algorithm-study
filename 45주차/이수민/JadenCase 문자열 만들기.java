class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for(int i=0;i<s.length();i++){
            if(cnt==0&&s.charAt(i)>='a'&&s.charAt(i)<='z'){
                String tmp = ""+s.charAt(i);
                sb.append(tmp.toUpperCase());
                cnt=1;
            }else if(cnt==1&&s.charAt(i)>='A'&&s.charAt(i)<='Z'){
                String tmp = ""+s.charAt(i);
                sb.append(tmp.toLowerCase());
                cnt=1;
            }else if(s.charAt(i)==' '){
                sb.append(" ");
                cnt=0;
            }else {
                sb.append(s.charAt(i));
                cnt=1;
            }
        }
        return sb.toString();
    }
}
