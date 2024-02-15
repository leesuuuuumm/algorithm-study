#include <iostream>
#include <math.h>

using namespace std;

void move(int from, int to){
    cout << from << " " << to << '\n';
}

/*int hanoicnt(int N){
    if(N == 1){
        return 1;
    }
    else
        return 2*hanoicnt(N - 1) + 1;
}*/

void hanoi(int N, int from, int by, int to){
    if(N == 0)
        return;
    hanoi(N - 1, from, to, by);
    move(from, to);
    hanoi(N - 1, by, from, to);
}

int main(){
    int N;
    cin >> N;

    string a = to_string(pow(2, N));

    int x = a.find('.');

    a = a.substr(0, x);

    a[a.length() - 1] = a[a.length() - 1] - 1;

    cout << a;
    if(N <= 20){
        cout << '\n';
        hanoi(N, 1, 2, 3);
    }
}