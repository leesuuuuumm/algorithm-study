import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point> {
		int num;
		int like;
		int time;

		public Point(int num, int like, int time) {
			this.num = num;
			this.like = like;
			this.time = time;
		}

		public int compareTo(Point o) {
			if (this.like == o.like) {
				return Integer.compare(this.time, o.time);
			}

			return Integer.compare(this.like, o.like);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TreeMap<Integer, Integer> map = new TreeMap<>();
		int[] arr = new int[101];

		PriorityQueue<Point> pq = new PriorityQueue<>();

		int size = Integer.parseInt(br.readLine());

		int M = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int cnt = 1;
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (map.size() == size) {
				if (!map.containsKey(num)) {
					for (Integer m : map.keySet()) {
						pq.offer(new Point(m, map.get(m), arr[m]));
					}

					Point del = pq.poll();

					arr[del.num] = 0;
					map.remove(del.num);
					map.put(num, 1);
					pq.clear();
					arr[num] = cnt++;

				} else {
					map.merge(num, 1, Integer::sum);
				}
			} else if (map.size() < size) {
				if(!map.containsKey(num)) {
					arr[num] = cnt++;

				}
				map.put(num, map.getOrDefault(num, 0) + 1);

			}

		}
		StringBuilder sb = new StringBuilder();
		for (Integer i : map.keySet()) {
			sb.append(i).append(" ");
		}
		System.out.println(sb);

	}
}
