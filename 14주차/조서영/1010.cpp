#include <iostream>
#include <algorithm>

using namespace std;

int main()
{
    int t, n, m;
    cin >> t;
    while(t > 0){
        cin >> n >> m;

        long long result = 1;

        int r = 1;
        for(int i = m; i > m - n; i--){
            result *= i;
            result /= r;
            r++;
        }
        cout << result << endl;
        t--;
    }
}