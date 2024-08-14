import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 두용액 {
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        long[] arr=new long[n];
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i]=Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        int left=0;
        int right=n-1;
        long min=Long.MAX_VALUE;
        String str="";
        while(left<right){
            long sum=arr[left]+arr[right];
            if(Math.abs(sum)<min){
                min=Math.abs(sum);
                str=arr[left]+" "+arr[right];
            }
            if(sum<0){
                left++;
            }else if(sum>0)
                right--;
            else break;
        }
        System.out.println(str);
    }
}
