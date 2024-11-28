package algorithm.study;

import java.util.*;
import java.io.*;

public class Solution {
 

    static int T;
    static int stick;
    static int pieces;
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T= Integer.parseInt(br.readLine());
        
        for (int t = 1; t <=T; t++) {
            String s=br.readLine();
            char ch[] =s.toCharArray();
            stick=0;
            pieces=0;
            char prev='j';
            for (int i = 0; i < ch.length; i++) {
                if(ch[i]=='(') {
                    stick++;
                }
                else if(ch[i]==')'){
                    if(prev=='(') { //레이저
                        stick--;
                        pieces+=stick;
                    }else{
                        stick--;
                        pieces++;
                    }
                }
                prev=ch[i];
            }
            System.out.println("#"+t+" "+pieces);
        }
    }
}

