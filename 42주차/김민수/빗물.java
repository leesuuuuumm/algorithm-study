import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 빗물 {
    static int w;
    static int[] height;
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int h=Integer.parseInt(st.nextToken());
        w=Integer.parseInt(st.nextToken());
        boolean[][] isBlock=new boolean[w][h+1];
        st=new StringTokenizer(br.readLine());
        height=new int[w];
        for(int i=0;i<w;i++){
            height[i]=Integer.parseInt(st.nextToken());
            for(int he=1;he<=height[i];he++){
                isBlock[i][he]=true;
            }
        }
        int result=0;
        for(int i=0;i<w;i++){
            int minHeight=Math.min(getRightMaxHeight(i),getLeftMaxHeight(i));
            if(minHeight>height[i]){
                result+=(minHeight-height[i]);
            }
        }

        System.out.println(result);
    }
    public static int getRightMaxHeight(int idx){
        if(idx==w-1){
            return height[idx];
        }
        int maxHeight=0;
        for(int i=idx;i<w;i++){
            maxHeight=Math.max(maxHeight, height[i]);
        }
        return maxHeight;
    }
    public static int getLeftMaxHeight(int idx){
        if(idx==0)
            return height[idx];
        int maxHeight=0;
        for(int i=0;i<idx;i++){
            maxHeight=Math.max(maxHeight, height[i]);
        }
        return maxHeight;
    }
}
