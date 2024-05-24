#include <iostream>
using namespace std;	
int cost[10001];
int parent[10001];
bool visit[10001];
int N, M,k;

int find(int a)
{
	if (parent[a] == a)
		return a;
	return parent[a] = find(parent[a]);
}

void Union(int a, int b)
{
	a = find(a);
	b = find(b);
	if (a != b)
	{
		if (cost[a] >= cost[b])
			parent[a] = b;
		else
			parent[b] = a;
	}
}
int main(void)
{
	cin >> N >> M>>k;
	for (int i = 1; i<= N; i++)
	{
		int c;
		parent[i]=i;
		cin >> c;
		cost[i] = c;
	}
	for (int i = 0; i < M; i++)
	{
		int a, b;
		cin >> a >> b;
		Union(a, b);
	}

	int res = 0;
	for (int i = 1; i <= N; i++)
	{
		int cur = find(i);
		if (!visit[cur]) {
			res += cost[cur];
			visit[cur] = true;
		}
	}

	if (res > k)
		cout << "Oh no";
	else
		cout << res;

}