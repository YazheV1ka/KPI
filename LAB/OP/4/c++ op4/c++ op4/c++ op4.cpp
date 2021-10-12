#include <iostream>
using namespace std;

int main()
{
    setlocale(LC_ALL, "rus");
    const float sin = 0.479426; //sin(0.5)
    float y = 0, sum = 0;
    int n = 0, i = 1;
    cout << "Введите n: ";//кількість ітерацій
    cin >> n;
    for (i = 1; i <= n; i++)
    {
        y = 1 / (sin / (pow(i, 2)));//загальна формула
        sum += y;
        cout << "Результат: " << sum << endl;
    }
    cout <<"Финальний результат: " << sum<< endl;
    
}
    
