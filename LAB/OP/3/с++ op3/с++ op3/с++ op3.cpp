#include <cmath>
#include <iostream>

using namespace std;
/*
int fact(int N)
{
    if (N < 0) 
        return 0; 
    if (N == 0) 
        return 1; 
    else 
        return N * fact(N - 1); //робимо рекурсію.
}*/


int main()
{
    setlocale(LC_ALL, "rus");
    float x = 0, m = 0;
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
    float diff = 0; //різниця між ітераціями
    float result = 1; //результат кожної ітерації(за умовою відразу = 1)

    int i = 1, fact = 1;

   
    
    do 
    {
        while (i <= power)//факторіал
        {
            fact *= i;
            i++;
        }
        diff = step;
        mult *= (m - count);
        step = (mult * pow(x, power)) / fact;
        power++;
        count++;
        result += step;
    } while (abs(step - diff) > accur);
    cout << result;
                      
}

