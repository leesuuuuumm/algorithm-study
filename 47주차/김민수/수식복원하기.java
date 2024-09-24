import java.util.*;

class 수식복원하기 {
    static boolean[] flag;
    public String[] solution(String[] expressions) {
        flag=new boolean[10];
        for(int i=2;i<=9;i++){
            flag[i]=true;
        }
        ArrayList<String> result=new ArrayList<>();
        int max=0;
        for(String st:expressions){
            String[] arr=st.split(" ");
            int A=Integer.parseInt(arr[0]);
            int B=Integer.parseInt(arr[2]);
            max=Math.max(max, getMaxNum(A));
            max=Math.max(max, getMaxNum(B));
            if(arr[4].equals("X")){

            }else{
                int C=Integer.parseInt(arr[4]);
                String op=arr[1];
                max=Math.max(max, getMaxNum(C));
            }
        }
        if(max>=2){
            for(int i=2;i<=max;i++){
                flag[i]=false;
            }
        }
        for(String st:expressions){
            String[] arr=st.split(" ");
            int A=Integer.parseInt(arr[0]);
            int B=Integer.parseInt(arr[2]);
            max=getMaxNum(A);
            max=Math.max(max,getMaxNum(B));
            if(arr[4].equals("X")){
                result.add(st);
            }else{
                int C=Integer.parseInt(arr[4]);
                String op=arr[1];
                max=Math.max(max, getMaxNum(C));
                calculate(A,B,C,op);
            }
        }


        for(int i=0;i<result.size();i++){
            String[] arr=result.get(i).split(" ");
            int A=Integer.parseInt(arr[0]);
            int B=Integer.parseInt(arr[2]);
            String tmp=getResult(A,B,arr[1]);
            result.set(i, A+" "+arr[1]+" "+B+" = "+tmp);
        }
        String[] answer = new String[result.size()];
        for(int i=0;i<result.size();i++){
            answer[i]=result.get(i);
        }
        return answer;
    }
    static int getMaxNum(int num){
        int quot=num;
        int rest=0;
        int max=0;
        while(quot>0){
            rest=quot%10;
            quot=quot/10;
            max=Math.max(rest,max);
        }
        return max;
    }
    static int trans(int origin, int i){
        int newNum=0;
        int idx=0;
        int quot=origin;
        int rest=0;
        while(quot>0){
            rest=quot%10;
            quot=quot/10;
            newNum+=(int)Math.pow(i,idx)*rest;
            idx+=1;
        }
        return newNum;
    }
    public static int trans10toX(int num, int X){
        int newNum=0;
        int idx=0;
        int quot=num;
        int rest=0;
        while(quot>0){
            rest=quot%X;
            quot=quot/X;
            newNum+=(int)Math.pow(10,idx)*rest;
            idx+=1;
        }
        return newNum;
    }
    public static String getResult(int A, int B, String op){
        int result=0;
        for(int i=2;i<=9;i++){
            if(flag[i]){
                int newA=trans(A,i);
                int newB=trans(B,i);
                if(op.equals("+")){
                    int temp=trans10toX(newA+newB, i);
                    if(result==0||result==temp){
                        result=temp;
                    }else{
                        return "?";
                    }
                }else{
                    int temp=trans10toX(newA-newB, i);
                    if(result==0||result==temp){
                        result=temp;
                    }else{
                        return "?";
                    }
                }
            }
        }
        return String.valueOf(result);
    }

    public static void calculate(int A, int B, int C, String op){
        for(int i=2;i<=9;i++){
            if(flag[i]){
                int newA=trans(A,i);
                int newB=trans(B,i);
                int newC=trans(C,i);
                if(op.equals("+")){
                    if(newC!=newA+newB){
                        flag[i]=false;
                    }
                }else{
                    if(newC!=newA-newB){
                        flag[i]=false;
                    }
                }

            }
        }

    }
}