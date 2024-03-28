#include <iostream>
using namespace std;

int n, target, sum, answer;
int arr[20];

void dfs(int i, int sum) {

    if (i == n) return;

    if (sum + arr[i] == target) answer++;

    dfs(i + 1, sum);

    dfs(i + 1, sum + arr[i]);
}

int main() {

    cin >> n >> target;
    
    for (int i = 0; i < n; i++)cin >> arr[i];

    answer = 0;
    dfs(0, 0);

    cout << answer;

    return 0;
}