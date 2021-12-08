import random
random.seed(0)


b = [random.uniform(-10, 10) for _ in range(5)]

print(b)
for index, number in enumerate(b):
    if number < 0:
        part_after_dot = str(number).split('.')[-1]
        b[index] = sum(map(int, part_after_dot))
print(b)

# [6.888437030500963, 5.159088058806049, -1.5885683833831, -4.821664994140733, 0.22549442737217085]
# [6.888437030500963, 5.159088058806049, 69, 67, 0.22549442737217085]
