for num in range(10,100): #тисячі та сотні
    a = num % 10
    b = num  // 10
    for num2 in range(100): #десяткі та одиниці
        c= num2 % 10
        d = num2  // 10
        if a!=b and a!=c and b!=c and d!=a and d!=c and d!=b:
            print(num*100+num2)
