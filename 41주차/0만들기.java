import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class 0만들기 {
    public static StringBuilder sb;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        sb=new StringBuilder();
        for(int i=0;i<t;i++){
            int n=sc.nextInt();
            bfs(n);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void bfs(int n){
        Queue<Str> que=new ArrayDeque<>();
        que.add(new Str("1",1,1, 1, 0));
        while(!que.isEmpty()){
            Str node=que.poll();
            int index=node.index+1;
            if(node.index>n)
                return;
            if(node.index==n&&node.result==0){
                sb.append(node.str+"\n");
            }
            //이어붙이기
            if(node.symbol==0){ //더하기
                que.add(new Str(node.str+" "+index, node.result-node.lastNum+node.lastNum*10+index, node.lastNum*10+index, index, node.symbol));
            }else if(node.symbol==1){
                que.add(new Str(node.str+" "+index, node.result+node.lastNum-(node.lastNum*10+index), node.lastNum*10+index, index, node.symbol));
            }
            //더하기
            que.add(new Str(node.str+"+"+index,node.result+index, index, index, 0));
            //빼기
            que.add(new Str(node.str+"-"+index, node.result-index, index, index, 1));

        }

    }

    public static class Str{
        public String str;
        public int result;
        public int lastNum;
        public int index;
        public int symbol;

        public Str(String str, int result, int lastNum, int index, int symbol){
            this.str=str;
            this.result=result;
            this.lastNum=lastNum;
            this.index=index;
            this.symbol=symbol;
        }
    }
}
