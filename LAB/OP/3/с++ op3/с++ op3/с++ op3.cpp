#include <cmath>
#include <iostream>
using namespace std;

int main()
{
    setlocale(LC_ALL, "rus");
    float x = 0;
    int m = 0;
    int n = 0;
    cout << "Введите x: ";
    cin >> x;
    cout << "Введите m: ";
    cin >> m;
    cout << "Введите точность 10 ^ n: ";// змінна степеню точності
    cin >> n;

    float accur = pow(10, n); // змінна точності
    int power = 1; //степінь та факторіал
    int count = 0;  //лічильник
    float mult = 1; //створення множників в чисельнику
    float step = 0; //обчислення за формулою
    float result = 1; //результат


    int i = 1, fact = 1;


    do 
    {

        while (i <= power)//факторіал
        {
            fact *= i;
            i++;
        }

        mult *= (m - count);//чисельник
        step = (mult * pow(x, power)) / fact;//загальна формула
        power++;
        count++;
        result += step;
        
        cout << "Результат: " << result << endl;
    } while (abs(step) > accur);//точність

                      
}

