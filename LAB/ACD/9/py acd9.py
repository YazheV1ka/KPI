import random

def generate_matrix(row, col):
    matrix = [[round(random.uniform(-20, 11), 3) for j in range(col)] for i in range(row)]
    return matrix

def output_matrix(matrix):
    print('Matrix')
    for row in matrix:
        print(*row, sep='\t\t')

def find_positive_elem(matrix, row, col):
    for j in range(col):
        for i in range(row) if (j % 2 == 0) else range(row - 1, -1, -1):
            if matrix[i][j] > 0:
                return matrix[i][j], i, j

def average_over_side_diagonal(matrix, size):
    suma = 0
    count = 0
    for i in range(size):
        for j in range(size):
            if (i + j) <= size:
                suma += matrix[i][j]
                count += 1

    return round((suma / count), 3)

def main():
    row = int(input("Enter a number of rows: "))
    col = int(input("Enter a number of columns: "))
    matrix = generate_matrix(row, col)
    positive_elem, row_index, col_index = find_positive_elem(matrix, row, col)
    aver = average_over_side_diagonal(matrix, row)

    output_matrix(matrix)
    print(f'\nFirst positive element = matrix[{row_index}][{col_index}] = {positive_elem}')
    print(f"Average over side diagonal = {aver}")
    print("First positive element is bigger") if (positive_elem > aver) else print("Average is bigger")
main()
