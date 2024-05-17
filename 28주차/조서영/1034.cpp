#include <iostream>
#include <string>
#include <unordered_map>
#include <algorithm>
using namespace std;

int n, m, k, answer = 0;
int main() {
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);
	//k는 0<=k <=1000
	//n과 m은 1<= nm <=50
	cin >> n >> m;
	unordered_map<string, pair<int, int>> ma;
	//ma.first ==pattern
	//ma.second.first==pattern_count
	//ma.second.second==0_count
	for (int i = 0; i < n; i++) {
		string str;
		cin >> str;
		ma[str].first++;
		if (ma[str].second == 0) {
			int cnt = 0;
			for (int j = 0; j < str.length(); j++) {
				if (str[j] == '0')
					cnt++;
			}
			ma[str].second = cnt;
		}
	}
	cin >> k;

	for (auto o : ma) {
		int cnt = o.second.second;
		if (cnt <=k && cnt %2 == k%2) {
			answer = max(answer, o.second.first);
			//ma.erase(o.first);
		}
	}
	
	cout << answer;
	return 0;
}
