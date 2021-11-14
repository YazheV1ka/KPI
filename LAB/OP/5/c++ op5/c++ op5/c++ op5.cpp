#include <iostream>;
using namespace std;

int main() 
{
    int num, num2 = 0;
    int a = 0, b = 0, c = 0, d = 0;
    for (num = 10; num < 100; num++) //тисячі та сотні
    {
        a = num % 10; //тисячі
        b = num / 10; //сотні
        for (num2 = 0; num2 < 100; num2++) //десяткі та одиниці
        {
            c = num2 % 10; //десяткі
            d = num2 / 10; //одиниці
            if (a != b && a != c)
            {
                if (b != c && d != a)
                {
                    if (d != c && d != b)
                    {
                        cout << (num * 100 + num2) << endl;
                    }
                }
            }
        }
    }
 
}



/*if (a != b and a != c and b != c and d != a and d != c and d != b)
{
    cout << num << endl;
}*/