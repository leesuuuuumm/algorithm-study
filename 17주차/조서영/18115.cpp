#include <iostream>
#include <vector>
#include <deque>

using namespace std;

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0); cout.tie(0);

	//입력
	int n; cin >> n;
	vector<int>v(n);
	for (int i = 0; i < n; i++) {
		cin >> v[i];
	}
	deque<int>dq;

	int card = 1;
	for (int i = n - 1; i >= 0; i--) {

		if (v[i] == 1) {
			dq.push_back(card);
		}else if (v[i] == 2) {
			int tmp = dq.back();
			dq.pop_back();
			dq.push_back(card);
			dq.push_back(tmp);
		}else if (v[i] == 3) {
			dq.push_front(card);
		}

		card++;
	}

	for (int i = n-1; i >=0; i--) cout << dq[i] << ' ';
	cout << '\n';
}