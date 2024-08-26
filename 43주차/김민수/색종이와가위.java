import java.io.InputStreamReader;
import java.util.Scanner;

public class 색종이와가위 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long cut=sc.nextLong();
        long slice=sc.nextLong();

        long lo=0;
        long hi=cut/2;
        while(lo<=hi){
            long row=(lo+hi)/2;
            long col=cut-row;

            long total=cutNum(col, row);
            if(total==slice){
                System.out.println("YES");
                return;
            }else if(total<slice){
                lo=row+1;
            }else{
                hi=row-1;
            }
        }
        System.out.println("NO");

    }
    static long cutNum(long col, long row){
        return (row+1)*(col+1);
    }
}
