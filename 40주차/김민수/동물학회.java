import java.util.Scanner;

public class 동물학회 {
    public static final int MAX_N=202;
    public static int[][] grid=new int[MAX_N][MAX_N];
    public static int N;
    public static int[][][] parallel=new int[MAX_N][MAX_N][MAX_N];  // parallel[i][j][k]: (i,1) ~ (j,k) 범위 내에서 최대 한개의 직사각형 영역으로 가질 수 있는 최대 가치를 저장합니다
    public static int[][][] vertical=new int[MAX_N][MAX_N][MAX_N];  // vertical[i][j][k]: (1,i) ~ (j,k) 범위 내에서 최대 한개의 직사각형 영역으로 가질 수 있는 최대 가치를 저장합니다

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        N=sc.nextInt();
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                grid[i][j]=sc.nextInt();
            }
        }

        makeParallel();
        makeVertical();

        int answer=Integer.MIN_VALUE;
        for(int row=1;row<=N;row++){
            answer=Math.max(answer, parallel[1][row][N]+parallel[row+1][N][N]);
        }

        for(int col=1;col<=N;col++){
            answer=Math.max(answer, vertical[1][N][col]+vertical[col+1][N][N]);
        }

        System.out.println(answer);
    }
    public static void makeParallel(){
        //시작 행 설정
        for(int startRow=1;startRow<=N;startRow++){
            //각 열의 누적합을 저장할 임시 배열
            int[] temp=new int[N+1];

            //끝 행 설정
            for(int endRow=startRow;endRow<=N;endRow++){
                //각 열의 누적 합 저장
                for(int c=1;c<=N;c++){
                    temp[c]+=grid[endRow][c];
                }

                int currentSum=0;
                int maxEndingHere=Integer.MIN_VALUE;

                //최대 연속 부분 배열 합 구하
                for(int c=1;c<=N;c++){
                    currentSum+=temp[c];
                    if(currentSum<0){
                        currentSum=0;
                    }
                    if(currentSum>maxEndingHere){
                        maxEndingHere=currentSum;
                    }
                    parallel[startRow][endRow][c]=Math.max(parallel[startRow][endRow][c], maxEndingHere);
                }
            }
        }

        //(startRow, 1) ~ (endRow, c) 범위 내에서 최대 한개의 직사각형 영역으로 가질 수 있는 최대 가치
        for(int d=2;d<=N;d++){
            for(int startRow=1;startRow<=N-d+1;startRow++){
                for(int c=1;c<=N;c++){
                    int endRow=startRow+d-1;
                    parallel[startRow][endRow][c]=Math.max(parallel[startRow][endRow][c],parallel[startRow+1][endRow][c]);
                    parallel[startRow][endRow][c]=Math.max(parallel[startRow][endRow][c], parallel[startRow][endRow-1][c]);
                }
            }
        }
    }


    public static void makeVertical(){
        //시작 열 설정
        for(int startCol=1;startCol<=N;startCol++){
            //각 행의 누적합을 저장할 임시 배열
            int[] temp=new int[N+1];

            //끝 열 설정
            for(int endCol=startCol;endCol<=N;endCol++){
                //각 행의 누적 합 저장
                for(int r=1;r<=N;r++){
                    temp[r]+=grid[r][endCol];
                }

                int currentSum=0;
                int maxEndingHere=Integer.MIN_VALUE;

                //최대 연속 부분 배열 합 구하
                for(int r=1;r<=N;r++){
                    currentSum+=temp[r];
                    if(currentSum<0){
                        currentSum=0;
                    }
                    if(currentSum>maxEndingHere){
                        maxEndingHere=currentSum;
                    }
                    //(1,startCol)~(r,endCol)
                    vertical[startCol][r][endCol]=Math.max(vertical[startCol][r][endCol], maxEndingHere);
                }
            }
        }

        //(startRow, 1) ~ (endRow, c) 범위 내에서 최대 한개의 직사각형 영역으로 가질 수 있는 최대 가치
        for(int d=2;d<=N;d++){
            for(int startCol=1;startCol<=N-d+1;startCol++){
                for(int r=1;r<=N;r++){
                    int endCol=startCol+d-1;
                    vertical[startCol][r][endCol]=Math.max(vertical[startCol][r][endCol],vertical[startCol+1][r][endCol]);
                    vertical[startCol][r][endCol]=Math.max(vertical[startCol][r][endCol], vertical[startCol][r][endCol-1]);
                }
            }
        }
    }


}
