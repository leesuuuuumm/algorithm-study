import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 좋다 {
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        StringTokenizer st=new StringTokenizer(br.readLine());
        int result=0;
        ArrayList<Long> input=new ArrayList<>();
        HashSet<Long> makeNum=new HashSet<>();
        for(int i=0;i<n;i++){
            long num=Long.parseLong(st.nextToken());
            input.add(num);
        }
        Collections.sort(input);
        for(int i=0;i<n;i++){

            long target=input.get(i);
            int left=0;
            int right=n-1;
            while(left<right){
                if(left==i){
                    left++;
                    continue;
                }
                if(right==i){
                    right--;
                    continue;
                }
                long sum=input.get(left)+input.get(right);
                if(sum==target){
                    result++;
                    break;
                }else if(sum<target){
                    left++;
                }else{
                    right--;
                }

            }
        }
        System.out.println(result);
    }
}
