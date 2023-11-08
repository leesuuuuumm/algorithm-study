package boj;

public class Main_10775_공항 {

	static int G, P;
	static int[] gates;

	public static void main(String[] args) throws Exception {
		G = read();
		P = read();
		gates = new int[G + 1];
		for (int i = 0; i <= G; i++) {
			gates[i] = i;
		}
		int result = 0;
		for (int i = 0; i < P; i++) {
			int allowGate = read();
			int idleGate = find(allowGate);

			if (idleGate == 0)
				break;

			result++;
			union(idleGate - 1, idleGate);
		}
		System.out.println(result);
	}

	private static int find(int a) {
		if (gates[a] == a)
			return a;

		return gates[a] = find(gates[a]);
	}

	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa == pb)
			return;

		gates[pb] = pa;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
