#include <iostream>
using namespace std;

int N, M;
int A[10001];

int countM() {
	int cnt = 0;
	int startx = 0;
	int endx = 0;
	int sum = 0;
	while (endx <= N) {
		if (sum < M) {
			sum += A[endx];
			endx++;
		}
		if (sum >= M) {
			if (sum == M)
				cnt++;
			sum -= A[startx];
			startx++;
		}
	}
	return cnt;
}

int main() {
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		cin >> A[i];
	}
	cout << countM() << '\n';
	return 0;
}