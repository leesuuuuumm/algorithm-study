import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 놀이기구탑승 {
    static int[] dx={-1, 0, 1, 0};
    static int[] dy={0, -1, 0, 1};
    static int[][] map;
    static int n;
    static HashMap<Integer, int[]> friendMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        map=new int[n][n];
        friendMap=new HashMap<>();
        for(int i=0;i<n*n;i++){
            st=new StringTokenizer(br.readLine());
            int idx=Integer.parseInt(st.nextToken());
            int f1=Integer.parseInt(st.nextToken());
            int f2=Integer.parseInt(st.nextToken());
            int f3=Integer.parseInt(st.nextToken());
            int f4=Integer.parseInt(st.nextToken());
            int[] friend=new int[4];
            friend[0]=f1;
            friend[1]=f2;
            friend[2]=f3;
            friend[3]=f4;
            friendMap.put(idx, friend);
            search(idx);
        }
        int result=0;
        int[] score=new int[5];
        score[0]=0;
        score[1]=1;
        score[2]=10;
        score[3]=100;
        score[4]=1000;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int x=i;
                int y=j;
                int count=0;
                for(int d=0;d<4;d++){
                    int nx=x+dx[d];
                    int ny=y+dy[d];
                    if(isInRange(nx,ny)&&isFriend(map[x][y], map[nx][ny])){
                        count+=1;
                    }
                }
                result+=score[count];
            }
        }
        System.out.println(result);

    }

    public static void search(int idx){
        PriorityQueue<Student> pq=new PriorityQueue<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int x=i;
                int y=j;
                if(map[x][y]!=0) //이미 선택된건 제외
                    continue;
                //인접한 방향으로 친구의 수, 비어있는 자리 수 카운트
                int friend=0;
                int vacant=0;
                for(int d=0;d<4;d++){
                    int nx=x+dx[d];
                    int ny=y+dy[d];
                    if(isInRange(nx,ny)){
                        if(map[nx][ny]!=0){
                            if(isFriend(idx, map[nx][ny])){
                                friend+=1;
                            }
                        }else{
                            vacant+=1;
                        }
                    }
                }
                pq.add(new Student(friend, vacant, x, y));
            }
        }

        Student s=pq.poll();
        map[s.r][s.c]=idx;
    }

    public static boolean isFriend(int idx, int friendIdx){
        int[] friendArr=friendMap.get(idx);
        for(int i=0;i<4;i++){
            if(friendArr[i]==friendIdx){
                return true;
            }
        }
        return false;
    }

    public static boolean isInRange(int nx, int ny){
        return (nx>=0&&ny>=0&&nx<n&&ny<n);
    }
    public static class Student implements Comparable<Student>{
        public int friend; //4방향에 있는 친구의 수
        public int vacant;// 4방향에 비어있는 칸 수 :격자 벗어나는 건 비어있는 칸 아님
        public int r, c;

        public Student(int friend, int vacant, int r, int c){
            this.friend=friend;
            this.vacant=vacant;
            this.r=r;
            this.c=c;
        }

        @Override
        public int compareTo(Student o) {
            if(o.friend==this.friend){
                if(o.vacant==this.vacant){
                    if(o.r==this.r){
                        return this.c-o.c;
                    }
                    return this.r-o.r;
                }
                return (o.vacant-this.vacant);
            }
            return (o.friend-this.friend);
        }
    }
}
