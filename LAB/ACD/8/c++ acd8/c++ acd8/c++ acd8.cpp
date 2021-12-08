#include <iostream>
#include <ctime>

using namespace std;

void input_matrix(const int ROW, const int COLS, int** p_mtx);

void Shell_Sort(int n, float arr2[]);

void print_arr(const int COLS, float arr2[]);

void print_matrix(const int COLS, const int ROW, int** p_mtx);

void proccess_matrix(const int ROW, const int COLS, int calc, int colvo, int** p_mtx, float arr2[]);

int main()
{
	const int ROW = 5;
	const int COLS = 8;
	int matrix[ROW][COLS];
	float arr[COLS];
	int calc = 0, k = 0;
	float colvo = 0;
	int* p_mtx[ROW];
	for (int i = 0; i < ROW; ++i) 
	{
		p_mtx[i] = matrix[i];
	}
	srand(time(NULL));

	input_matrix(ROW, COLS, p_mtx);
	proccess_matrix(ROW, COLS, calc, colvo, p_mtx, arr);

	cout << "Previous matrix: " << endl;
	print_matrix(COLS, ROW, p_mtx);
	cout << endl;

	cout << "Massive of neg arith : " << endl;
	print_arr(COLS, arr);
	cout << endl << endl;

	cout << "Sorted massive of neg arith : " << endl;
	Shell_Sort(COLS, arr);
	print_arr(COLS, arr);

	return 0;
}
void Shell_Sort(int n, float arr2[])
{
	int i, j, step;
	float tmp;
	for (step = n / 2; step > 0; step /= 2)
	{
		for (i = step; i < n; i++)
		{
			tmp = arr2[i];
			for (j = i; j >= step; j -= step)
			{
				if (tmp > arr2[j - step])
					arr2[j] = arr2[j - step];
				else
					break;
			}
			arr2[j] = tmp;
		}
	}
}

void proccess_matrix(const int ROW, const int COLS, int calc, int colvo, int** p_mtx, float arr[])
{
	for (int j = 0; j < COLS; j++)
	{
		for (int k = 0; k < ROW; k++)
		{
			if (p_mtx[0 + k][j] < 0)
			{
				calc += p_mtx[0 + k][j];
				colvo++;
			}

		}
		arr[j] = calc / colvo;
		calc = 0;
		colvo = 0;
	}
}

void input_matrix(const int ROW, const int COLS, int** p_mtx)
{
	for (int i = 0; i < ROW; i++)
	{
		for (int j = 0; j < COLS; j++)
		{
			p_mtx[i][j] = rand() % 101 - 50;
		}
	}
}

void print_arr(const int COLS, float arr[])
{
	for (int j = 0; j < COLS; j++)
	{
		cout << arr[j] << "\t";
	}
}

void print_matrix(const int COLS, const int ROW, int** p_mtx)
{
	for (int i = 0; i < ROW; i++)
	{
		for (int j = 0; j < COLS; j++)
		{
			cout << p_mtx[i][j] << "\t";
		}
		cout << endl;
	}
}