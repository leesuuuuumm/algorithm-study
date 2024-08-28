import java.io.*;
import java.util.*;

public class 염기서열커버 {
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        char[][] input=new char[n][m];
        for(int i=0;i<n;i++){
            String str=br.readLine();
            input[i]=str.toCharArray();
        }

        char[] makeNewStr=new char[m];
        for(int i=0;i<m;i++){
            makeNewStr[i]='g';
        }
        ArrayList<char[]> result=new ArrayList<>();
        result.add(input[0]);
        boolean flag=true;
        for(int i=1;i<n;i++){
            for(char[] gene:result){
                char[] str=getLongString(input[i], gene);
                if(!isEqual(str,makeNewStr)){
                    gene=str;
                    flag=false;
                    break;
                }
            }
            if(flag){
                result.add(input[i]);
            }

        }
        System.out.println(result.size());
    }



    public static char[] getLongString(char[] a, char[] b){
        for(int i=0;i<m;i++){
            if(a[i]=='.'&&b[i]!='.'){
                a[i]=b[i];
            }else if(a[i]!='.'&&b[i]!='.'&&a[i]!=b[i]){
                a=new char[m];
                for(int k=0;k<m;k++){
                    a[k]='g';
                }
                return a;
            }
        }
        return a;
    }

    public static boolean isEqual(char[] a, char[] b){
        for(int i=0;i<m;i++){
            if(a[i]!=b[i])
                return false;
        }
        return true;
    }


}
