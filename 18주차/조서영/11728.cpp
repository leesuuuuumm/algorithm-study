#include <iostream>

using namespace std;

int n,m;
int a_index=0, b_index=0;

int main() {
    ios_base::sync_with_stdio(0); 
    cin.tie(0); 
    cout.tie(0);

    cin >>n>>m;

    int a[n+1],b[m+1];

    for (int i=0;i<n;i++) {
        cin >> a[i];
    }
    for (int i=0;i<m;i++) {
        cin >> b[i];
    }

    while(a_index < n && b_index < m) {
        if(a[a_index]<=b[b_index]) {
            cout << a[a_index++] << " ";
        } else {
            cout << b[b_index++] << " ";
        }
    }
    while(a_index<n) {
        cout << a[a_index++] << " ";
    }
    while(b_index<m) {
        cout << b[b_index++] << " ";
    }
}