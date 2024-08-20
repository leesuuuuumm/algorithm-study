import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 연산자끼워넣기 {
    static int max, min, n;
    static int[] num;
    static int[] operatorNum;
    static int[] operator;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());
        num=new int[n];
        StringTokenizer st=new StringTokenizer(br.readLine());
        max=Integer.MIN_VALUE;
        min=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            num[i]=Integer.parseInt(st.nextToken());
        }
        operatorNum=new int[4]; //0: 더하기, 1: 뺄셈, 2: 곱셈, 3: 나누기
        operator=new int[n-1];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<4;i++){
            operatorNum[i]=Integer.parseInt(st.nextToken());
        }
        backtracking(0);
        System.out.println(max);
        System.out.println(min);
    }
    public static void backtracking(int depth){
        if(depth==n-1){
            int result=num[0];
            for(int i=0;i<n-1;i++){
                if(operator[i]==0){
                    result+=num[i+1];
                }else if(operator[i]==1){
                    result-=num[i+1];
                }else if(operator[i]==2){
                    result*=num[i+1];
                }else{
                    if(result*num[i+1]<0){
                        result=-Math.abs(result)/Math.abs(num[i+1]);
                    }else{
                        result/=num[i+1];
                    }
                }
            }
            min=Math.min(result,min);
            max=Math.max(result,max);
        }
        for(int i=0;i<4;i++){
            if(operatorNum[i]!=0){
                operator[depth]=i;
                operatorNum[i]-=1;
                backtracking(depth+1);
                operatorNum[i]+=1;
            }
        }
    }
}
