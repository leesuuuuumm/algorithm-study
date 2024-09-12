// [BOJ] 회문

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void Solution(String str){
        str = str.toLowerCase(Locale.ROOT);

        StringBuilder builder = new StringBuilder(str).reverse();
        if(builder.toString().equals(str)){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }

    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        String input = in.next();

        Solution(input);
    }