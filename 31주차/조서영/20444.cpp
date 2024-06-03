#include <bits/stdc++.h>
#define X first
#define Y second
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
const ll INF = 1e18;
const int inf = 2e9;
const int SIZE = 1 << 18;
const int MOD = 1e9 + 7;

int main(void){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	
	ll n, k;
	cin >> n >> k;
	ll lo = 0;
	ll hi = n / 2;
	ll mid;
	bool ans = false;
	while (lo <= hi) {
		mid = (lo + hi) >> 1; // mid 는 점차 중앙값에 근접해가기
		ll cnt = (mid + 1) * (n - mid + 1);
		if (cnt == k) {
			ans = true;
			break;
		}
		// 만약 값이 k보다 크다면 mid 값 낮추기
		else if (cnt > k) hi = mid - 1;
		// 만약 값이 k보다 작다면 mid 값 올리기
		else lo = mid + 1;
	}
	if (ans) cout << "YES";
	else cout << "NO";
	return 0;
}