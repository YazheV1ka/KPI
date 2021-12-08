#include <iostream>
using namespace std;

int NCD(int a, int b);

int main()
{
    int n, i, m;
    cout << "n = ";
    cin >> n;
    cout << "i = ";
    cin >> i;
    cout << "m = ";
    cin >> m;
    cout << "NCD: " << NCD(NCD(n, i), m); 
}

int NCD(int a, int b) //НCД
{
    while (a != b)
    {
        if (a > b)
            a -= b;
        else
            b -= a;
    }
    return a;
}