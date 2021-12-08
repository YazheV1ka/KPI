def NCD(a, b): #НCД
     while a != b:
          if a > b:
             a -= b
          else:
               b -= a
     return a 
print ("n = ")
n = int(input())
print ("i = ")
i = int(input())
print ("m = ")
m = int(input())
print("NCD: ",NCD(NCD(n, i), m))
