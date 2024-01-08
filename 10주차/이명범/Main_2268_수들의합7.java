package boj;

public class Main_2268_수들의합7 {

	static class SegmentTree {
		long[] tree;
		int treeSize;

		public SegmentTree(int arrSize){
			int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
			this.treeSize = (int) Math.pow(2, h + 1);
			tree = new long[treeSize];
		}

		public long init(long[] arr, int node, int start, int end){

			if (start == end) {
				return tree[node] = arr[start];
			}

			return tree[node] =
				init(arr, node * 2, start, (start + end) / 2)
					+ init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
		}

		public void update(int node, int start, int end, int idx, long diff){
			if (idx < start || end < idx)
				return;

			tree[node] += diff;

			if (start != end) {
				update(node * 2, start, (start + end) / 2, idx, diff);
				update(node * 2 + 1, (start + end) / 2 + 1, end, idx, diff);
			}
		}

		public long sum(int node, int start, int end, int left, int right) {
			if (left > end || right < start) {
				return 0;
			}

			if (left <= start && end <= right) {
				return tree[node];
			}

			return sum(node * 2, start, (start + end) / 2, left, right) +
				sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		}
	}

	static int N, M;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		N = read();
		M = read();
		arr = new int[N + 1];

		SegmentTree st = new SegmentTree(N + 1);

		StringBuilder sb = new StringBuilder();
		for (int c = 0; c < M; c++) {
			int com = read();
			if (com == 0) {
				int a = read();
				int b = read();
				int i = Math.min(a, b);
				int j = Math.max(a, b);
				sb.append(st.sum(1, 1, N, i, j)).append("\n");
			} else if (com == 1) {
				int i = read();
				int k = read();
				int diff = k - arr[i];
				st.update(1, 1, N, i, diff);
				arr[i] = k;
			}
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
