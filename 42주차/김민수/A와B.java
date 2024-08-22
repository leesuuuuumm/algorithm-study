import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class A와B {
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Character> S=new ArrayList<>();
        char[] input1=br.readLine().toCharArray();
        for(int i=0;i<input1.length;i++){
            S.add(input1[i]);
        }
        ArrayList<Character> T=new ArrayList<>();
        char[] input2=br.readLine().toCharArray();
        for(int i=0;i<input2.length;i++){
            T.add(input2[i]);
        }
        for(int i=T.size()-1;i>=S.size();i--){
            if(T.get(i)=='A'){
                T.remove(i);
            }else{
                T.remove(i);
                Collections.reverse(T);
            }
        }
        for(int i=0;i<S.size();i++){
            if(S.get(i)!=T.get(i)) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(1);

    }

}
