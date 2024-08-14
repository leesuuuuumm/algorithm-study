import java.util.*;
class Solution {
    class Point{
        int r;
        int c;
        
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public int solution(int[] priorities, int location) {
	  ArrayList<Integer> list = new ArrayList<Integer>(); 
		int[] answer = new int[priorities.length]; 
		Queue<Point> que = new LinkedList<>();
		for (int i = 0; i < priorities.length; i++) {
			list.add(priorities[i]);
			que.offer(new Point(i, list.get(i)));
		}
		int tmp = 0;
		while (!que.isEmpty()) {
			boolean flag = false;
			a: for (int i = 1; i < list.size(); i++) {
				if (list.get(0) < list.get(i)) { 
					Point cur = que.poll();
					que.offer(new Point(cur.r, cur.c));
					int k = list.get(0);
					list.remove(0);
					list.add(k);

					flag = true;
					break a;
				}
			}
		if(!flag) {
			Point cur= que.poll();
			answer[tmp++] = cur.r;
			list.remove(0);
		}
		}
		int ans = 0;
		for(int i=0;i<answer.length;i++) {
			if(location==answer[i]) {
				System.out.println(i+1);
				ans = i+1;
			}
		}
      
    return ans;
    }
}
