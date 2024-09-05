import java.io.*;
import java.util.*;

public class 성적평균 {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] st=br.readLine().split(" ");
        StringBuilder sb=new StringBuilder();
        int N=Integer.parseInt(st[0]);
        int K=Integer.parseInt(st[1]);
        int[] score=new int[N+1];
        int[] sum=new int[N+1];
        String[] st2=br.readLine().split(" ");
        for(int i=1;i<=N;i++){
            score[i]=Integer.parseInt(st2[i-1]);
        }
        sum[1]=score[1];
        for(int i=2;i<=N;i++){
            sum[i]=sum[i-1]+score[i];
        }

        for(int i=0;i<K;i++){
            String[] st3=br.readLine().split(" ");
            int start=Integer.parseInt(st3[0]);
            int end=Integer.parseInt(st3[1]);
            int prefixSum=sum[end]-sum[start-1];
            double result=(double)prefixSum/(end-start+1);
            String med=String.format("%.2f", result);
            sb.append(med).append("\n");
        }
        System.out.println(sb);
    }

}
