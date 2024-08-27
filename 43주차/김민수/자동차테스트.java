import java.io.*;
import java.util.*;

public class 자동차테스트 {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int q=Integer.parseInt(st.nextToken());
        long[] arr=new long[n];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i]=Long.parseLong(st.nextToken());
        }
        StringBuilder sb=new StringBuilder();
        long[] mInput=new long[q];
        for(int i=0;i<q;i++){
            st=new StringTokenizer(br.readLine());
            mInput[i]=Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);
        Map<Long, Integer> map=new HashMap<>();
        long[][] count=new long[n][2];
        int left=0;
        int right=(n-1);
        for(int i=0;i<n;i++){
            map.put(arr[i], i); //map의 key: 값(long), value: idx
            count[i][0]=left;
            count[i][1]=right;
            left+=1;
            right-=1;
        }

        for(int i=0;i<q;i++){
            long result=0;
            if(map.get(mInput[i])!=null){
                int idx=map.get(mInput[i]);
                result= (long) count[idx][0] *count[idx][1];
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);

    }
}