import java.util.*;

class 하노이의탑 {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(solution(4)));
    }
    public static int[][] solution(int n) {
        ArrayList<int[][]> sum=new ArrayList<>();
        int count=1;
        int idx=1;
        while(n<idx){
            count=2*count+1;
            idx+=1;
        }

        int[][] answer1=new int[1][2];
        answer1[0][0]=1;
        answer1[0][1]=3;
        sum.add(answer1);

        int[][] answer2=new int[3][2];
        answer2[0][0]=1;
        answer2[0][1]=2;
        answer2[1][0]=1;
        answer2[1][1]=3;
        answer2[2][0]=2;
        answer2[2][1]=3;
        sum.add(answer2);
        if(n<=2){
            return sum.get(n-1);
        }else{
            idx=3;
            int prevCount=3;
            while(idx<=n){
                count=prevCount*2+1;
                int[][] answer=new int[count][2];
                int[][] prevAnswer=sum.get(idx-2);
                for(int i=0;i<prevCount;i++){
                    for(int j=0;j<2;j++){
                        int value=prevAnswer[i][j];
                        if(prevAnswer[i][j]==3){
                            value=2;
                        }else if(prevAnswer[i][j]==2){
                            value=3;
                        }
                        answer[i][j]=value;
                    }
                }
                answer[prevCount][0]=1;
                answer[prevCount][1]=3;

                for(int i=0;i<prevCount;i++){
                    for(int j=0;j<2;j++){
                        int value=prevAnswer[i][j];
                        if(prevAnswer[i][j]==1){
                            value=2;
                        }else if(prevAnswer[i][j]==2){
                            value=1;
                        }
                        answer[i+prevCount+1][j]=value;
                    }
                }
                sum.add(answer);
                prevCount=count;
                idx+=1;
            }
            return sum.get(n-1);
        }
    }
}