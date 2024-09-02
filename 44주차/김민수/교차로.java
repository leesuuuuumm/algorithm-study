import java.io.*;
import java.util.*;

public class 교차로 {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        Queue<Car>[] cars=new Queue[4];
        for(int i=0;i<4;i++){
            cars[i]=new ArrayDeque<>();
        }
        for(int i=0;i<N;i++){
            String[] str=br.readLine().split(" ");
            int road=str[1].charAt(0)-'A';
            cars[road].add(new Car(i, Integer.parseInt(str[0])));
        }

        int[] result=new int[N];
        Arrays.fill(result, -1);
        int curTime=-1;
        while(true){
            if(cars[0].isEmpty()&&cars[1].isEmpty()&&cars[2].isEmpty()&&cars[3].isEmpty()){
                break; //모두 통과
            }

            int minTime=Integer.MAX_VALUE; //4개 큐 t중 가장 작은거 값
            int[] state=new int[4]; //이동 가능?
            for(int i=0;i<4;i++){
                if(!cars[i].isEmpty()){
                    int t=cars[i].peek().time;
                    minTime=Math.min(t, minTime);

                    if(t<=curTime){ //차가 밀려있음
                        state[i]=1;
                    }
                }
            }

            int count=0;
            for(int s:state){
                count+=s;
            }
            if(count==4){//교착상태
                break;
            }else if(count==0){
                //비어있음
                curTime=minTime;
            }else{// 밀려있음 / 이동 가능
                for(int i=0;i<4;i++){
                    if(state[i]==1&&state[(i+3)%4]==0){
                        result[cars[i].poll().id]=curTime;
                    }
                }
                curTime+=1;
            }

        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<N;i++){
            sb.append(result[i]).append("\n");
        }
        System.out.println(sb);

    }
    public static class Car{
        public int id, time;
        Car(int id, int time){
            this.id=id;
            this.time=time;
        }
    }
}
