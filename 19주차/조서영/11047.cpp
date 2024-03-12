#include <iostream>
using namespace std;

int main() {
	int N, K;
	int A[10]; //동전의 가치

	cin >> N >> K;

	for (int i = 0; i < N; i++) {
		cin >> A[i];
	} //A1 = 1, Ai는 A(i-1)의 배수이다. 

	int count = 0;
	for (int i = N - 1; i >= 0; i--) {
		if (A[i] <= K) {
			count = count + K / A[i];
			K = K % A[i];
		}
		if (K == 0)
			break;
	}

	cout << count << endl;

	return 0;
}