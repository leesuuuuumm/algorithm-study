import java.util.*;

class Solution {
    class Point implements Comparable<Point>{
        String name;
        int st;
        int pt;
        
        public Point(String name, int st, int pt){
            this.name = name;
            this.st = st;
            this.pt = pt;
        }
        public Point(String name, int pt){
            this.name = name;
            this.pt = pt;
        }
        
        public int compareTo(Point o){
            return Integer.compare(this.st, o.st);
        }
        
    }
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        PriorityQueue<Point> pq = new PriorityQueue<>();
        
        for(int i=0;i<plans.length;i++){
            String[] str = plans[i][1].split(":");
            int time = Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
            pq.offer(new Point(plans[i][0],time,Integer.parseInt(plans[i][2])));
        }
        Stack<Point> stack = new Stack<>();
        int i=0;
        while(true){
            if(pq.size() == 0){ 
                while(!stack.isEmpty()){
                    answer[i] = stack.pop().name;
                    i++;
                }
                break;
            }else{
                if(pq.size() == 1){
                    answer[i] = pq.poll().name;
                    i++;
                    continue;
                }
                Point cur = pq.poll();
                int time = pq.peek().st - cur.st;
                
                if(time < cur.pt){
                    stack.push(new Point(cur.name, cur.pt-time));
                }else if(time == cur.pt){
                    answer[i] = cur.name;
                    i++;
                }else if(time> cur.pt){ 
                    answer[i] =cur.name;
                    i++;
                    time-=cur.pt;
                        while(!stack.isEmpty()){
                            Point next = stack.pop();
                            
                            if(time<next.pt){
                                stack.push(new Point(next.name, next.pt-time));
                                break;
                            }
                            time-=next.pt;
                            answer[i] = next.name;
                            i++;
                    }
                }
            }
        } 
        return answer;
    }
}
