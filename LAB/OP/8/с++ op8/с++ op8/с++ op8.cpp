#include <iostream>
#include <ctime>

using namespace std;

void init_matrix(int** a, int n);

void fill_matrix(int** a, int n);

void process_matrix(int** a, int n);

void output_matrix(int** a, int n);

int main()
{
    srand((time(NULL)));

    int n = 0;
    cout << "Enter num of colums and rows = ";
    cin >> n;
    int** a = new int* [n];
    init_matrix(a, n);
    fill_matrix(a, n);
    cout << "Original Matrix:  \n";
    output_matrix(a, n);
    process_matrix(a, n);
    cout << "Final Matrix:  \n";
    output_matrix(a, n);
    system("pause");
    return 0;
}

void init_matrix(int** a, int n)
{
    for (int i = 0; i < n; i++)//строки
    {
        a[i] = new int[n];
    }
}

void fill_matrix(int** a, int n)
{
    for (int i = 0; i < n; i++)//строки
    {
        for (int j = 0; j < n; j++)//столбцы    
        {
            a[i][j] = rand() % 10;
        }
    }
}

void process_matrix(int** a, int n)
{
    for (int i = 0; i < n; i++)//строки
    {
        for (int j = 0; j < n; j++)//столбцы    
        {
            for (int k = 0; k < n - i; k++)//нулевые столбцы    
            {
                if (a[i + k][j] == 0)
                {
                    for (int q = 0; q < n; q++)//замена  
                    {
                        a[0 + q][j] = a[0 + q][0 + q];
                    }
                }
            }
        }
    }
}

void output_matrix(int** a, int n)
{
    for (int i = 0; i < n; i++)//строки
    {
        for (int j = 0; j < n; j++)//столбцы
        {
            cout << a[i][j] << "\t";
        }
        cout << endl; 
    }
}