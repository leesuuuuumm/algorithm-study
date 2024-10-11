import java.util.*;

class 정수삼각형 {
    public int solution(int[][] triangle) {
        int len=triangle.length;
        for(int l=len-1;l>=1;l--){
            int[] num=triangle[l];
            for(int i=0;i<l;i++){
                int first=num[i];
                int second=num[i+1];
                int max=Math.max(first, second);
                triangle[l-1][i]+=max;
            }
        }
        return triangle[0][0];
    }
}