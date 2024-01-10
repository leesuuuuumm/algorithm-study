package boj;

public class Main_2637_장난감조립 {
	static class Node {
		int num;
		int[] needs;
		boolean leaf;

		public Node(int num, int[] needs) {
			this.num = num;
			this.needs = needs;
			this.leaf = true;
		}

		public void add(int val, int count) {
			needs[val] += count;
			leaf = false;
		}

		public boolean isLeaf() {
			return leaf;
		}

		public int[] calculate() {
			for (int i = 1; i <= N; i++) {
				if (needs[i] == 0)
					continue;
				if (nodes[i].isLeaf())
					continue;

				int[] res = nodes[i].calculate();
				for (int j = 1; j <= N; j++) {
					needs[j] += needs[i] * res[j];
				}
				needs[i] = 0;
			}
			return needs;
		}
	}

	static int N, M;
	static Node[] nodes;

	public static void main(String[] args) throws Exception {
		N = read();
		M = read();
		nodes = new Node[N + 1];
		for (int i = 1; i <= N; i++) {
			nodes[i] = new Node(i, new int[N + 1]);
		}
		for (int i = 1; i <= M; i++) {
			int idx = read();
			int val = read();
			int count = read();
			nodes[idx].add(val, count);
		}
		int[] calculated = nodes[N].calculate();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < N; i++) {
			if (calculated[i] == 0)
				continue;
			sb.append(i).append(" ").append(calculated[i]).append("\n");
		}
		System.out.print(sb);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
