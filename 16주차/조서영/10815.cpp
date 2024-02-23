#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main()
{
    int n, m, num;
    int pf, pl, pk;
    scanf("%d", &n);
    vector<int> card;
    for(int i = 0; i < n; i++){
        int a;
        cin >> a;
        card.push_back(a);
    }
    sort(card.begin(), card.end());
    scanf("%d", &m);
    
    for(int i = 0; i < m; i++){
        pf = 0;
        pl = n - 1;
        pk = (pf + pl) / 2;
        scanf("%d", &num);
        while(1){
            if(num == card[pk]){
                printf("1 ");
                break;
            }
            if(num > card[pk]){
                pf = pk + 1;
                pk = (pf + pl) / 2;
            }
            else{
                pl = pk - 1;
                pk = (pf + pl) / 2;
            }
            if(pf > pl){
                printf("0 ");
                break;
            }
        }
    }
}