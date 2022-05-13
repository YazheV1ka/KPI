string = input("Enter a string: ")
k = int(input("Enter a k: "))
list_= string.split(' ')
list_2 = []
for i in list_:
    if len(i)<=k:
        list_2.append(i)
for i in range(len(list_2)):
               print(list_2[i], end=' ')
