#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <sstream>
#include <cmath>
#include <queue>
#include <set>

using namespace std;

int main()
{
	int N;
	cin >> N;

	vector<int> vec;
	vector<int> vecDP;
	vec.assign(N, 0);
	vecDP.assign(N, 1);

	for (int i = 0; i < N; i++)
		cin >> vec[i];

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < i; j++)
		{
			if (vec[i] < vec[j])
				vecDP[i] = max(vecDP[j] + 1, vecDP[i]);
		}
	}

	cout << *max_element(vecDP.begin(), vecDP.end());

	return 0;
}