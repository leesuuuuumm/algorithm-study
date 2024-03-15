import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static int N;
    static int[] front;
    static ArrayList<ArrayList<Integer>> al;
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); //학생 수
        int M = sc.nextInt();
        front = new int[N]; //해당 학생 앞에 몇명이 서있어야 하는지
        al = new ArrayList<>(); //해당 학생의 뒤에 누가 서있는지
        for(int i = 0; i < N; i++){
            al.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            int first = sc.nextInt()-1;
            int second = sc.nextInt()-1;
            al.get(first).add(second); //첫번째 주어진 학생 뒤에 두번째 학생이 서야 함
            front[second]++; //두번째 학생 앞에 한명이 추가됨
        }

        makeLine();
    }

    public static void makeLine() throws IOException {
        Queue<Integer> q = new LinkedList<>();

        for(int i = 0; i < N; i++){
            if(front[i] == 0){
                q.offer(i); //앞에 아무도 없으면 줄 대기
            }
        }

        //bufferedwriter를 사용 안하고 answer += string으로 하면 메모리 초과 발생, 왜?
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while(!q.isEmpty()){
            int no = q.poll(); //줄 설 학생
            bw.write((no+1) + " ");

            for(int i = 0; i < al.get(no).size(); i++){
                front[al.get(no).get(i)]--; //한명이 줄을 섰으니 감소
                if(front[al.get(no).get(i)] == 0){ //앞에 아무도 앞으면 줄 대기
                    q.offer(al.get(no).get(i));
                }
            }
        }
        bw.flush();
        bw.close();
    }
}
