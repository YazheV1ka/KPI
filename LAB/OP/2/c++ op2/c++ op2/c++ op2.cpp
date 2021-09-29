#include <iostream>
using namespace std;

int main()
{
	setlocale(LC_ALL, "Ukr");
	int num1 = 0, num2 = 0, num3 = 0;
	cout << "Введите 3 числа:";
	cin >> num1; //Вводимо 1 число
	cin >> num2; //Вводимо 2 число
	cin >> num3; //Вводимо 3 число
	
	if (num1 > num2 && num3 < num2) //1 число більше другого, 3 менше 2
		cout << num1 + num2;

	if (num1 > num2 && num3 > num2) //1 число більше другого, 3 більше 2
		cout << num1 + num3;

	if (num1 < num2 && num3 < num1) //1 число менше другого, 3 менше 1
		cout << num2 + num1;
	
	if (num1 < num2 && num3 > num1) //1 число менше другого, 3 більше 1
		cout << num2 + num3;
}

