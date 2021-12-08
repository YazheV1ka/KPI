#include <iostream>
#include <ctime>
using namespace std;

int sum_of_part(double num);

void fill_array(double B[], int size);

void process_array(double B[], int size);

void output_array(double B[], int size);


int main() {
    srand((time(NULL)));
    int size;
    cout << "How many numbers: ";
    cin >> size;
    double* B = new double[size];
    fill_array(B, size);
    cout << "Previous massive: " << endl;
    output_array(B, size);
    process_array(B, size);
    cout << "Final massive: " << endl;
    output_array(B, size);
    system("pause");
}

int sum_of_part(double num) 
{
    double _num;
    int part = round(abs(modf(num, &_num)) * pow(10, 6)); //Помножує дробову частину, щоб вона стала цілою
    int sum = 0;
    while (part) //сумма дробової частини
    {
        sum += part % 10;
        part /= 10;
    }
    return sum;
}

void fill_array(double B[], int size) 
{
    for (int i = 0; i < size; ++i) 
    
    {
        B[i] = ((rand() % 201 - 101) / 1000.0) + round(rand() % 101 - 50); //заповнення масиву дійсними
    }
}

void process_array(double B[], int size) 
{
    for (int i = 0; i < size; ++i) 
    {
        if (B[i] < 0)
        {
            B[i] = sum_of_part(B[i]);
        }
        else
        {
            B[i] = B[i];
        }
    }
}

void output_array(double B[], const int size)
{
    for (int i = 0; i < size; ++i)
    {
        cout << "[" << i << "]: " << B[i] << endl;
    }
}