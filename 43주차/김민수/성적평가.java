import java.io.*;
import java.util.*;

public class 성적평가 {
    public static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Participant[][] contest=new Participant[4][n];
        int[] totalScore=new int[n];
        for(int i=0;i<3;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                int score=Integer.parseInt(st.nextToken());
                contest[i][j]=new Participant(j, score);
                totalScore[j]+=score;
            }
        }
        for(int j=0;j<n;j++){
            contest[3][j]=new Participant(j, totalScore[j]);
        }
        sb=new StringBuilder();
        for(int i=0;i<4;i++){
            calculateAndPrintRanks(contest[i], n);
        }

        System.out.println(sb);

    }

    public static class Participant implements Comparable<Participant>{
        public int idx;
        public int score;

        Participant(int idx, int score){
            this.idx=idx;
            this.score=score;
        }

        @Override
        public int compareTo(Participant o){
            return o.score-this.score;
        }

    }

    private static void calculateAndPrintRanks(Participant[] participants, int n) {
        Arrays.sort(participants);

        int[] rank=new int[n];
        int curRank=1;
        int sameRankCount=1;
        rank[participants[0].idx]=curRank;

        for(int i=1;i<n;i++){
            if(participants[i].score<participants[i-1].score){
                curRank+=sameRankCount;
                rank[participants[i].idx]=curRank;
                sameRankCount=1;
            }else{
                rank[participants[i].idx]=curRank;
                sameRankCount+=1;
            }
        }
        for(int i=0;i<n;i++){
            sb.append(rank[i]).append(" ");
        }
        sb.append("\n");
    }
}