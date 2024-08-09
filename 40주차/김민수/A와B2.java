import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class A와B2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String input=sc.next();
        String result=sc.next();
        Queue<String>que=new ArrayDeque<>();
        que.add(result);
        while(!que.isEmpty()){
            String str=que.poll();
            if(str.length()<input.length()){
                System.out.println("0");
                return;
            }
            if(str.equals(input)){
                System.out.println("1");
                return;
            }
            if(str.charAt(str.length()-1)=='A'){
                que.add(str.substring(0,str.length()-1));
            }
            if(str.charAt(0)=='B'){
                String temp=str.substring(1, str.length());
                StringBuffer sb=new StringBuffer(temp);
                que.add(sb.reverse().toString());
            }
        }
        System.out.println("0");
    }
}
