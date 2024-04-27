#include <bits/stdc++.h>
#define pci pair<char,int>

using namespace std;

string zipString;

void solve() {
    cin >> zipString;
    stack<pci> s;
    int strSize = 0;

    for (char c : zipString) {
        if (c == ')') {
            while (1) {
                if (s.top().first == '(') {
                    s.pop();
                    int repeatCount = s.top().first - '0';
                    s.top().second = repeatCount * strSize;
                    strSize = 0;
                    break;
                }
                strSize += s.top().second;
                s.pop();
            }
        }
        else s.push({ c, 1 });
    }
    int answer = 0;
    while (!s.empty()) {
        answer += s.top().second;
        s.pop();
    }
    cout << answer << "\n";
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);  cout.tie(NULL);
    freopen("input.txt", "r", stdin);

    solve();

    return 0;
}