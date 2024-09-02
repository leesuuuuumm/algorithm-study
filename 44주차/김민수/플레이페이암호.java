import java.io.*;
import java.util.*;

public class 플레이페이암호 {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        char[] m=sc.next().toCharArray();
        ArrayList<Character> message=new ArrayList<>();
        for(int i=0;i<m.length;i++){
            message.add(m[i]);
        }
        char[] key=sc.next().toCharArray();
        char[][] map=new char[5][5];
        Map<Character, int[]> alphabetPos=new HashMap<>();
        int row=0;
        int col=0;
        //1. 문자열 만들기
        for(char k:key){
            if(k=='J'){
                k='I';
            }
            if(!alphabetPos.containsKey(k)){
                if(col==5){
                    row+=1;
                    col=0;
                }
                map[row][col]=k;
                alphabetPos.put(k, new int[]{row, col});
                col+=1;
            }
        }
        if(!((row==4)&&(col==5))){//남아있음 채워야 하는게
            for(int i=0;i<=25;i++){
                char k=(char)('A'+i);
                if(k=='J'){
                    k='I';
                }
                if(!alphabetPos.containsKey(k)){
                    if(col==5){
                        row+=1;
                        col=0;
                    }
                    map[row][col]=k;
                    alphabetPos.put(k, new int[]{row, col});
                    col+=1;
                }
            }
        }

        //2. 2글자씩 나누기
        for(int i=0;i<message.size();i+=2){
            if(i<message.size()-1){
                char left=message.get(i);
                char right=message.get(i+1);
                if(left==right){
                    if(left=='X'){
                        message.add(i+1, 'Q');
                    }else{
                        message.add(i+1, 'X');
                    }
                }
            }else{
                message.add(i+1, 'X');
            }
        }

        //3. 암호화
        Character[] mArr=message.toArray(new Character[message.size()]);
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<message.size();i+=2){
            char left=mArr[i];
            char right=mArr[i+1];
            int[] leftPos=alphabetPos.get(left);
            int[] rightPos=alphabetPos.get(right);
            if(leftPos[0]==rightPos[0]){//같은 행
                char changeLeft=map[leftPos[0]][(leftPos[1]+1)%5];
                char changeRight=map[rightPos[0]][(rightPos[1]+1)%5];
                mArr[i]=changeLeft;
                mArr[i+1]=changeRight;
                sb.append(changeLeft).append(changeRight);
            }else if(leftPos[1]==rightPos[1]){//같은 열
                char changeLeft=map[(leftPos[0]+1)%5][leftPos[1]];
                char changeRight=map[(rightPos[0]+1)%5][rightPos[1]];
                mArr[i]=changeLeft;
                mArr[i+1]=changeRight;
                sb.append(changeLeft).append(changeRight);
            }else{
                char changeLeft=map[leftPos[0]][rightPos[1]];
                char changeRight=map[rightPos[0]][leftPos[1]];
                mArr[i]=changeLeft;
                mArr[i+1]=changeRight;
                sb.append(changeLeft).append(changeRight);
            }
        }
        System.out.println(sb);

    }
}
