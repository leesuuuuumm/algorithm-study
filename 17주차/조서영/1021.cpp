#include <iostream>
#include <deque>
#include <algorithm>

using namespace std;

int main()
{
    int n, m;
    int cnt = 0;
    int index = 0; // 인덱스를 스스로 생각하지 못한 것...
    cin >> n >> m;
    deque<int> deque;
    for(int i = 0; i < n; i++){
        deque.push_back(i+1);
    }
    for(int i = 0; i < m; i++){
        int b;
        cin >> b;
        for(int j = 0; j < deque.size(); j++){
            if(b == deque[j]){
                index = j;
                break;
            }
        }
        if(index <= (deque.size()/2)){ // 이거 왜 작거나 같을 때만? 크거나 같을 때랑 작거나 같은 거 동일한 거 아닌가
            while(1){
                if(b == deque.front()){
                    deque.pop_front();
                    break;
                }
                deque.push_back(deque.front());
                deque.pop_front();
                cnt++;
            }
        }
        else{
            while(1){
                if(b == deque.front()){
                    deque.pop_front();
                    break;
                }
                deque.push_front(deque.back());
                deque.pop_back();
                cnt++;
            }
        }
    }
    cout << cnt;
}