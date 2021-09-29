#include <iostream>
using namespace std;

int main()
{
	setlocale(LC_ALL, "Ukr");
	int num = 0, movnum = 0, staynum = 0, result = 0;
	cout << "Тризначне число:";
	cin >> num; //ввод тризначного числа
	movnum = num / 100; //знаходимо 1 цифру зліва
	staynum = num % 100; //знаходимо число без 1 цифри зліва
	result = staynum * 10 + movnum; //дописуємо цифру в кінець
	cout << "Результат:" << result;
}
