import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n과m_10 {

    public static int n, m;
    public static int[] input;
    public static StringBuilder sb;
    public static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());
        input=new int[n];
        result=new int[m];

        for(int i=0;i<n;i++){
            input[i]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(input);
        sb=new StringBuilder();
        dfs(0,0);
        System.out.println(sb);
    }
    public static void dfs(int start, int depth){
        if(depth==m){
            for(int val:result){
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }

        int prev=0;

        for(int i=start;i<n;i++){
            if(prev!=input[i]){
                prev=input[i];
                result[depth]=input[i];
                dfs(i+1, depth+1);
            }
        }
    }
}
