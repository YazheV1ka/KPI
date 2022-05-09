from random import *
from TPyramid import *
from TCylinder import *


def minS(arr):
    min_S = arr[0]
    for i in range(len(arr)):
        if arr[i] < min_S:
            min_S = arr[i]
    return min_S


def maxV(arr):
    max_V = arr[0]
    for i in range(len(arr)):
        if arr[i] > max_V:
            max_V = arr[i]
    return max_V


def main():
    print("Enter number of pyramids: ")
    n = int(input())
    print("Enter number of cylinders: ")
    m = int(input())

    pyramids = [TPyramid(randint(1, 20), randint(1, 20)) for i in range(n)]
    cylinders = [TCylinder(randint(1, 20), randint(1, 20)) for i in range(m)]


    # pyramids
    Squares = [Pyramid.S() for Pyramid in pyramids]
    # cylinders
    Volumes = [Cylinder.V() for Cylinder in cylinders]

    for pyramid in pyramids:
        print(pyramid)

    print("\n")

    for cylinder in cylinders:
        print(cylinder)

    print("\n")

    # min Square of Pyramids and min Volume of Cylinders
    minS = min(Squares)
    maxV = max(Volumes)
    print(f"Minimum Pyramid Square = {minS}")
    print(f"Maximum Cylinder Volume = {maxV}")


main()
