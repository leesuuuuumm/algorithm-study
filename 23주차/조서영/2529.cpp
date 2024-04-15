#include <iostream>

using namespace std;

int k;
char A[10];
bool check[10];
string maxr = "";
string minr = "9876543210";

void dfs(int n, string s) {
    if (s.size() == k + 1) {
        maxr = max(maxr, s);
        minr = min(minr, s);

        return;
    }

    for (int i = 0; i <= 9; i++) {
        if (check[i]) continue;
        if (A[s.size() - 1] == '>') {
            if (s.back() - '0' < i) continue;
        }
        if (A[s.size() - 1] == '<') {
            if (s.back() - '0' > i) continue;
        }

        check[i] = true;
        s.push_back(i + '0');
        dfs(i, s);
        s.pop_back();
        check[i] = false;
    }
}

void solution() {
    string s = "";

    for (int i = 0; i <= 9; i++) {
        check[i] = true;
        s.push_back((i + '0'));
        dfs(i, s);
        s.pop_back();
        check[i] = false;
    }

    cout << maxr << endl;
    cout << minr << endl;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    cin >> k;

    for (int i = 0; i < k; i++) {
        cin >> A[i];
    }

    solution();

    return 0;
}