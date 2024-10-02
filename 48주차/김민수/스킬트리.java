import java.util.*;
import java.io.*;

class 스킬트리 {
    static char[] skillArr;
    static boolean[] inSkill;
    public int solution(String skill, String[] skill_trees) {
        skillArr=skill.toCharArray();
        inSkill=new boolean[26];
        for(int i=0;i<skillArr.length;i++){
            int idx=skillArr[i]-'A';
            inSkill[idx]=true;
        }
        int answer=0;
        for(int i=0;i<skill_trees.length;i++){
            if(check(skill_trees[i])){
                answer+=1;
            }
        }
        return answer;
    }
    public static boolean check(String skill){
        char[] input=skill.toCharArray();
        int idx=0;
        for(int i=0;i<input.length;i++){
            if(idx==skillArr.length)
                return true;
            int alphabet=input[i]-'A';
            if(inSkill[alphabet]){
                //skill안의 알파벳이면
                if(skillArr[idx]==(input[i])){
                    idx+=1;
                }else{
                    return false;
                }
            }
        }
        return true;
    }
}