import java.util.*;
class Solution {
    class Point{
        int r;
        int c;
        
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this.getClass() != o.getClass()) return false;
            return (((Point) o).r == this.r) && (((Point) o).c == this.c);
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
        
    }
    
    static char[][] cBoard;
    static int[] dr = {1,0,1};
    static int[] dc = {0,1,1}; // 하, 우, 하우
    static int M,N;
    static boolean v ;
    static int answer;
    public int solution(int m, int n, String[] board) {
        cBoard = new char[n][m];
        answer = 0;
        M = m;
        N = n;
        for(int i=0;i<m;i++){
            cBoard[i] = board[i].toCharArray();
        }
        
        v = true;
        
        while(true){
            // 터트려주기 단계 
            popBlock();
            if(!v){
               break; 
            }
            // 블록 내려주기 단계
            downBlock();      
        }
        return answer;
    }
    
    private void popBlock(){
        HashSet<Point> hs = new HashSet<>();
        for(int r=0;r<M-1;r++){
            for(int c=0;c<N-1;c++){
                boolean flag = false;
                if(cBoard[r][c] != '0'){
                    for(int d= 0;d<3;d++){
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        
                        if(!check(nr,nc) || (cBoard[r][c] != cBoard[nr][nc])){
                           flag = true;
                            break;
                        }
                    }
                    if(!flag){
                        hs.add(new Point(r,c));
                        for(int d= 0;d<3;d++){
                            int nr = r + dr[d];
                            int nc = c + dc[d];
                            
                            hs.add(new Point(nr,nc));
                        }   
                    }
                    }
                }     
            }
            if(hs.size()==0){
                v = false;
            }else{
                addZero(hs);
            }
            
        }
    
    private void downBlock(){
           
        for(int i=0;i<N;i++){
            Queue<Character> que = new LinkedList<>();
            for(int j=M-1;j>=0;j--){
                if(cBoard[j][i] == '0'){
                    
                    for(int k=0;k<j;k++){
                        if(cBoard[k][i]!='0'){
                            que.offer(cBoard[k][i]); // 0이 아닐때만        
                            cBoard[k][i] = '0';
                        }
                    }
                    if(que.size()==0){
                        break ;
                    }
                    for(int k = j-que.size()+1; k<=j ;k++){
                        cBoard[k][i] = que.poll();
                    }
                    break;
                }
            }
        }
    }
    
    private void addZero(HashSet<Point> hs){
        for(Point p: hs){
            cBoard[p.r][p.c] = '0';
            answer+=1;
        }        
    }
    private boolean check(int nr,int nc){
        return nr>=0 || nr<M || nc>=0 ||nc<M;
    }
    

}
