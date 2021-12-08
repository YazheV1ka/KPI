#include <iostream>
using namespace std;

float sum(float first, float dif, int n);

int main()
{
    float first, dif, result;
    int n;
    cout << "Enter first number: ";
    cin >> first;
    cout << "Enter difference: ";
    cin >> dif;
    cout << "Enter number of numbers: ";
    cin >> n;
    result = sum(first, dif, n);
    cout << "Result: " << result << endl;
}

float sum(float first, float dif, int n)
{
    float sum2 = first;
    if (n > 1)
    {
        sum2 += sum(first + dif, dif, --n);
    }
    return sum2;
}
