#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#define MAX 1000001
#define FASTIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(false);

using namespace std;
int N, M, I, J, F;
string Cmd;
vector<int> Vec;
int Prev[MAX];
int Next[MAX];

void input() {
    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        cin >> I;
        Vec.push_back(I);
    }
    for (int i = 0; i < N; i++) {
        int Now = Vec[i];
        if (i == 0) {
            Prev[Now] = Vec[N - 1];
            Next[Now] = Vec[1];
        }
        else if (i == N - 1) {
            Prev[Now] = Vec[N - 2];
            Next[Now] = Vec[0];
        }
        else {
            Prev[Now] = Vec[i - 1];
            Next[Now] = Vec[i + 1];
        }
    }
    while (M--) {
        cin >> Cmd;
        if (Cmd == "BN") {
            cin >> I >> J;
            cout << Next[I] << "\n";
            Prev[J] = I;
            Next[J] = Next[I];
            Prev[Next[I]] = J;
            Next[I] = J;
        }
        else if (Cmd == "BP") {
            cin >> I >> J;
            cout << Prev[I] << "\n";
            Prev[J] = Prev[I];
            Next[J] = I;
            Next[Prev[I]] = J;
            Prev[I] = J;
        }
        else if (Cmd == "CN") {
            cin >> I;
            cout << Next[I] << "\n";
            int B = Next[Next[I]];
            Prev[Next[I]] = 0;
            Next[Next[I]] = 0;
            Next[I] = B;
            Prev[B] = I;
        }
        else if (Cmd == "CP") {
            cin >> I;
            cout << Prev[I] << "\n";
            int B = Prev[Prev[I]];
            Prev[Prev[I]] = 0;
            Next[Prev[I]] = 0;
            Prev[I] = B;
            Next[B] = I;
        }
    };
}

int main() {
    FASTIO
    input();

    return 0;
}