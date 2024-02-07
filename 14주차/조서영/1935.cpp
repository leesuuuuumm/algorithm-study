#include <iostream>
#include <stack>

using namespace std;

double opp(double x, char c, double y) { //사칙연산
	switch (c) {
	case '+':
		return x + y;
		break;

	case '-':
		return x - y;
		break;

	case '*':
		return x * y;
		break;

	case '/':
		return x / y;
		break;
	}
}

int main() {
	int N;
	string str; //후위 표기식
	double num[26]; //num[i] : i번째 피연산자에 대응하는 값
	stack<double> st;

	/*입력받기*/
	cin >> N;
	cin >> str;
	for (int i = 0; i < N; i++) {
		cin >> num[i];
	}

	/*계산하기*/
	double x, y;
	for (int i = 0; i < str.size(); i++) { //후위 표기식을 순회하면서
		if ('A' <= str[i] && str[i] <= 'Z') //피연산자이면
			st.push(num[str[i] - 'A']); //피연산자에 대응하는 값을 push
		else { //연산자이면
			y = st.top(); st.pop();
			x = st.top(); st.pop();
			st.push(opp(x, str[i], y)); //스택에서 값 2개 빼내서 연산 후 push
		}
	}

    /*출력하기*/
	cout << fixed; //소숫점 아래 값을 지정하겠다
	cout.precision(2); //그 값은 2
	cout << st.top();

	return 0;
}
/*#include <iostream>
#include <algorithm>
#include <vector>
#include <stack>

using namespace std;

int main()
{
    int N;
    cin >> N;

    string str;
    cin >> str;

    vector<int> num;
    stack<char> num_op;
    stack<char> op;
    stack<double> result;

    for(int i = 0; i < N; i++){
        int a;
        cin >> a;
        num.push_back(a);
    }

    int s = 0;

    for(int i = 0; i < str.size(); i++){
        if(isalpha(str[i])){
            num_op.push(str[i]);
        }
        else{
            if(!num_op.empty() && num_op.size() >= 2){
                s = num_op.size();

            }
            op.push(str[i];)
        }
    }
}*/