import java.util.*;

class 야근지수 {
	public long solution(int n, int[] works) {
		PriorityQueue<Integer> que=new PriorityQueue<>(Collections.reverseOrder());
		for(int i=0;i<works.length;i++){
			que.add(works[i]);
		}
		for(int i=0;i<n;i++){
			if(!que.isEmpty()){
				int q=que.poll();
				if(q-1>0)
					que.add(q-1);
			}
		}

		long answer = 0;
		int length=que.size();
		for(int i=0;i<length;i++){
			int num=que.poll();
			answer+=num*num;
		}
		return answer;

	}
}
