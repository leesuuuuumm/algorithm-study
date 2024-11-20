// [SOFT] 전광판

import java.util.*;
import java.io.*;

public class Main
{
    public static void main(String args[])    {
        Map<Character,String> lights = new HashMap<>();
        lights.put('0', "1110111");
        lights.put('1', "0010010");
        lights.put('2', "1011101");
        lights.put('3', "1011011");
        lights.put('4', "0111010");
        lights.put('5', "1101011");
        lights.put('6', "1101111");
        lights.put('7', "1110010");
        lights.put('8', "1111111");
        lights.put('9', "1111011");
        lights.put(' ', "0000000"); 
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] data = new int[n][2];
        for (int i=0; i<n; i++) {
            int c = sc.nextInt();
            int c_ = sc.nextInt();
            data[i][0] = c;
            data[i][1] = c_;
        }
        for (int i=0; i< n; i++) {
            int count = 0;

            String d1 = String.format("%5s",data[i][0]); 
            String d2 = String.format("%5s", data[i][1]);

            char[] d1_ = d1.toCharArray();
            char[] d2_ = d2.toCharArray();

            for (int j=0; j<5; j++) {
                count+=diff(lights.get(d1_[j]), lights.get(d2_[j]));
            }
            System.out.println(count);
        }

    }
 
    static int diff (String a, String b) {
        int count = 0;
        char[] a_ = a.toCharArray();
        char[] b_ = b.toCharArray();
        for (int i=0; i<a.length(); i++) {
            if (a_[i] != b_[i]) {
                count++;
            }
        }
        return count;
    }
}