#include <iostream>
#include <algorithm>

using namespace std;

int main()
{
	int N, M, pf, pl, pk;
	int num = 0;
	int j = 0;
	scanf("%d", &N);
	int A[N];
	for(int i = 0; i < N; i++){
		scanf("%d ", &A[i]);
	}
	sort(A, A + N);
	scanf("%d", &M);
	for(int i = 0; i < M; i++){
		scanf("%d", &num);
		pf = 0;
		pl = N - 1;
		pk = (pf + pl) / 2;
		while(1){
			if(A[pk] == num){
				printf("1\n");
				break;
			}
			if(A[pk] > num){
				pl = pk - 1;
				pk = (pf + pl) / 2;
				if(pf > pl){
					printf("0\n");
					break;
				}
			}	
			else{
				pf = pk + 1;
				pk = (pf + pl) / 2;
				if(pf > pl){
					printf("0\n");
					break;
				}
			}
		}
	}
}