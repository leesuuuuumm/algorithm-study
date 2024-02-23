#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main()
{
    int t, n, m, num;
    int pf, pl, pk;
    scanf("%d", &t);
    for(int i = 0; i < t; i++){
        scanf("%d", &n);
        vector<int> note;
        for(int j = 0; j < n; j++){
            int a;
            cin >> a;
            note.push_back(a);
        }
        sort(note.begin(), note.end());
        scanf("%d", &m);
        for(int j = 0; j < m; j++){
            pf = 0;
            pl = n - 1;
            pk = (pf + pl) / 2;
            scanf("%d", &num);
            while(1){
                if(num == note[pk]){
                    printf("1\n");
                    break;
                }
                
                if(num > note[pk]){
                    pf = pk + 1;
                    pk = (pf + pl) / 2;
                }
                else{
                    pl = pk - 1;
                    pk = (pf + pl) / 2;
                }

                if(pf > pl){
                    printf("0\n");
                    break;
                }
            }
        }
    }
}