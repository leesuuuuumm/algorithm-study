import java.util.*;
class Solution {
    public int solution(String name) {
        int answer = 0;
      
        char[] alph =  name.toCharArray();
        int updownCnt = 0;
        for(int i=0;i<alph.length;i++){
            if(alph[i]-'A' >= ('Z'-alph[i])+1){
                updownCnt+= ('Z'-alph[i])+1;
            }
            else{
                 updownCnt+= alph[i]-'A';
            }
        } 
        int len = alph.length;
        int lrCnt = len - 1;
        for(int i=0;i<alph.length;i++){
            if(i<len-1&&alph[i+1]=='A'){
                int aIdx = i+1;
                while(aIdx <len&&alph[aIdx]=='A'){
                    aIdx++;
                } 
