import java.util.*;
class Solution {
    public String solution(String p) {
        String answer = "";
        if(check(p)) return p;
        
        answer = pSplit(p);
        
        
           
        return answer;
    }
    private boolean check(String p){
        int cnt = 0;
        if(p.charAt(0)==')') return false;
        for(int i=0;i<p.length();i++){
            if(p.charAt(i)=='(') cnt++;
            else cnt--;
            if(cnt<0) return false;
        }
        return true;
    }
    private String pSplit(String p){
        StringBuilder u = new StringBuilder();
        StringBuilder v = new StringBuilder();
        
        if(p.length()==0) return "";
        
        int cnt =0;
        for(int i=0;i<p.length();i++){
            if(p.charAt(i)=='(')cnt++;
            else cnt--;
            
            if(cnt==0){
                u.append(p.substring(0,i+1)); 
                v.append(p.substring(i+1));
                    
                    if(check(u.toString())){
                        u.append(pSplit(v.toString()));
                    }else{
                        StringBuilder str = new StringBuilder();
                        str.append("(");
                        str.append(pSplit(v.toString()));
                        
                        str.append(")");
                        str.append(reverse(u.toString()));
                        return str.toString();
                    }
                break;
            }
        }
        return u.toString(); 
        
    }
    private String reverse(String str){
        StringBuilder u = new StringBuilder();
        
        for(int i=1;i<str.length()-1;i++){
            if(str.charAt(i)=='(') u.append(")");
            else u.append("(");
        }
        return u.toString();
    }
}
