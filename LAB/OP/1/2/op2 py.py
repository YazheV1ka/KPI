print("Введіть 3 числа:")
num1=int(input())
num2=int(input())
num3=int(input())
if (num1 > num2 and num3 < num2):   #1 цифра більше другої, 3 менше 2
    print(num1 + num2)

if (num1 > num2 and num3 > num2): #1 цифра більше другої, 3 більше 2
    print(num1 + num3)

if (num1 < num2 and num3 < num1): #1 цифра менше другої, 3 менше 1
    print(num2 + num1)
	
if (num1 < num2 and num3 > num1): #1 цифра менше другої, 3 більше 1
    print(num2 + num3)
