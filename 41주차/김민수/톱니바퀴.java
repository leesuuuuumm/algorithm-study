import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 톱니바퀴 {
    static int[][] wheel;
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        wheel=new int[4][8];
        for(int i=0;i<4;i++){
            String str=br.readLine();
            for(int j=0;j<8;j++){
                wheel[i][j]=Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }
        int n=Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            int startIdx=Integer.parseInt(st.nextToken());
            int dir=Integer.parseInt(st.nextToken());
            simulation(startIdx-1, dir);
        }

        int score=0;
        int idx=1;
        for(int i=0;i<4;i++){
           score+=(wheel[i][0])*(idx);
           idx*=2;
        }
        System.out.println(score);

    }

    static void simulation(int startIdx, int startDir){
        int[][] origin=new int[4][8];
        for(int i=0;i<4;i++){
            for(int j=0;j<8;j++){
                origin[i][j]=wheel[i][j];
            }
        }
        switch(startIdx){
            case 0:{
                if(origin[0][2]!=origin[1][6]) {
                    wheel[1]=rotate(origin[1],-startDir);
                    if(origin[1][2]!=origin[2][6]){
                        wheel[2]=rotate(origin[2],startDir);
                        if(origin[2][2]!=origin[3][6]){
                            wheel[3]=rotate(origin[3],-startDir );
                        }
                    }
                }
                wheel[0]=rotate(origin[0],startDir);
                break;
            }
            case 1:{
                if(origin[0][2]!=origin[1][6]){
                    wheel[0]=rotate(origin[0], -startDir);
                }if(origin[2][6]!=origin[1][2]){
                    wheel[2]=rotate(origin[2], -startDir);
                    if(origin[3][6]!=origin[2][2]){
                        wheel[3]=rotate(origin[3],startDir );
                    }
                }
                wheel[1]=rotate(origin[1],startDir);

                break;
            }
            case 2:{
                if(origin[1][2]!=origin[2][6]){
                    wheel[1]=rotate(origin[1],-startDir );
                    if(origin[0][2]!=origin[1][6]){
                        wheel[0]=rotate(origin[0],startDir );
                    }
                }
                if(origin[2][2]!=origin[3][6]){
                    wheel[3]=rotate(origin[3], -startDir);
                }
                wheel[2]=rotate(origin[2],startDir);

                break;
            }
            case 3:{
                if(origin[2][2]!=origin[3][6]){
                    wheel[2]=rotate(origin[2],-startDir );
                    if(origin[1][2]!=origin[2][6]){
                        wheel[1]=rotate(origin[1],startDir );
                        if(origin[0][2]!=origin[1][6]){
                            wheel[0]=rotate(origin[0],-startDir );
                        }
                    }
                }
                wheel[3]=rotate(origin[3],startDir);

                break;
            }
        }

    }
    static int[] rotate(int[] rotate, int dir){
        int[] result=new int[8];
        if(dir==1){//시계방향
            result[0]=rotate[7];
            for(int i=1;i<8;i++){
                result[i]=rotate[i-1];
            }
        }else{//반시계방향
            result[7]=rotate[0];
            for(int i=0;i<7;i++){
                result[i]=rotate[i+1];
            }
        }
        return result;
    }

}
