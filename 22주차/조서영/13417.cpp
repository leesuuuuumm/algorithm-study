#include <iostream>
#include <string>
using namespace std;
int T, N;
int main()
{
    ios::sync_with_stdio(0), cin.tie(0);
    cin >> T;
    while (T--)
    {
        cin >> N;
        string cards = "";
        char card;
        for (int i = 0; i < N; ++i)
        {
            cin >> card;
            cards = cards + card < card + cards ? cards + card : card + cards;
        }
        cout << cards << "\n";
    }
    return 0;
}