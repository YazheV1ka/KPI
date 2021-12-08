#include <iostream>

using namespace std;

int main()
{
    char a[10];
    char b[10];
    char c[10];
    int i,count=0;
    for (i = 0; i < 10; i++)
    {
        a[i] = 74 - i;
        b[i] = 65 + 2 * i;
    }

    cout << "Common symbols: " << endl;
    if (a[i] == b[i])
    {
        c[i] = a[i];
        cout << "c: " << c[i]<<endl;
        if ((int)c[i] < 67)
        {
            count++;
        }
    }
    cout << "Num of symbols: " << endl;
    cout << count;
}
