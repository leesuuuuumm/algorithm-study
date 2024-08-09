import java.util.Scanner;

public class 문자열교환 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str=sc.next();
        char[] array=str.toCharArray();
        int ALen=0;
        for(int i=0;i<array.length;i++){
            if(array[i]=='a')
                ALen+=1;
        }
        int totalLen=array.length;
        int result=Integer.MAX_VALUE;
        for(int i=0;i<totalLen;i++){
            int temp=0;
            for(int j=i;j<i+ALen;j++){
                if(array[j%totalLen]=='b')
                    temp+=1;
            }
            result=Math.min(temp, result);
        }
        System.out.println(result);
    }
}
