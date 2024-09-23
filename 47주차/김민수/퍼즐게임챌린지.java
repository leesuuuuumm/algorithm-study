import java.util.*;

class 퍼즐게임챌린지 {
    public int solution(int[] diffs, int[] times, long limit) {
        int[] sortDiff=diffs.clone();
        Arrays.sort(sortDiff);
        int answer = binarySearch(times, sortDiff, diffs, limit);
        return answer;
    }
    public static int binarySearch(int[] times, int[] sortDiff, int[] diffs, long limit){
        int len=diffs.length;
        int left=1;
        int right=100000;
        int mid=0;
        int result=0;
        while(left<=right){
            mid=(left+right)/2;
            long cal=calculate(mid,diffs,times);
            if(cal<=limit){
                result=mid;
                right=mid-1;
            }else{
                left=mid+1;
            }
        }
        return result;
    }

    public static long calculate(int level, int[] diffs, int[] times){
        int time_prev=times[0];
        int len=times.length;
        int time_cur=times[0];
        long result=0;
        for(int i=0;i<len;i++){
            if(diffs[i]<=level){
                result+=times[i];
            }else{
                int num=(diffs[i]-level);
                result+=(time_prev+times[i])*num+times[i];
            }
            time_prev=times[i];
        }
        return result;
    }
}