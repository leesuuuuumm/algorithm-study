#include <bits/stdc++.h>

using namespace std;

int main(void)
{
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	int n;
	cin >> n;
	
	vector<int> v(n);
	for (int i = 0; i < n; i++)
		cin >> v[i];

	int s = 0, e = n - 1, ans = 0;
	while (s <= e)
	{
		int between = e - s - 1;
		ans = max(ans, between * min(v[s], v[e]));

		if (v[e] > v[s])
			s++;
		else
			e--;
	}

	cout << ans << '\n';
	return 0;
}