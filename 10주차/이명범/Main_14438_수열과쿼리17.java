package boj;

public class Main_14438_수열과쿼리17 {

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
				Math.min(init(arr, node * 2, start, (start + end) / 2),
					init(arr, node * 2 + 1, (start + end) / 2 + 1, end));
		}

		public long update(int node, int start, int end, int idx, long value){
			if (idx < start || end < idx)
				return tree[node];

			if (start == end)
				return tree[node] = value;

			return tree[node] = Math.min(update(node * 2, start, (start + end) / 2, idx, value),
					update(node * 2 + 1, (start + end) / 2 + 1, end, idx, value));
		}

		public long min(int node, int start, int end, int left, int right) {
			if (left > end || right < start) {
				return Integer.MAX_VALUE;
			}

			if (left <= start && end <= right) {
				return tree[node];
			}

			return Math.min(min(node * 2, start, (start + end) / 2, left, right),
				min(node * 2 + 1, (start + end) / 2 + 1, end, left, right));
		}
	}

	static int N, M;
	static long[] A;
	static SegmentTree st;
	static StringBuilder result = new StringBuilder();

	public static void main(String[] args) throws Exception {
		N = read();
		A = new long[N + 1];
		st = new SegmentTree(N + 1);
		for (int i = 1; i <= N; i++) {
			A[i] = read();
		}
		st.init(A, 1, 1, N);
		M = read();
		for (int i = 0; i < M; i++) {
			command(read());
		}
		System.out.print(result);
	}

	private static void command(int mode) throws Exception {
		if (mode == 1) {
			int i = read();
			int v = read();

			st.update(1, 1, N, i, v);
		} else if (mode == 2) {
			int i = read();
			int j = read();

			result.append(st.min(1, 1, N, i, j)).append("\n");
		}
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
