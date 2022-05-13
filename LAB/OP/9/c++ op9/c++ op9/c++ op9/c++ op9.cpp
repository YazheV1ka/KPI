#include <iostream>
#include <string>

using namespace std;

int num_of_words(string str); // визначення к-сті слів в рядку

string* arr_of_words(string str, int size); // створення масиву зі слів

int find_new_size(string* arr, int size, int k); // знаходження розміру нового масиву

string* arr_of_words_less_than_k(string* arr, int size, int k); // створення нового масиву зі слів менших за k

void output_arr(string* arr, int size); // вивід масиву


int main() {
    string str;
    cout << "Input string: ";
    getline(cin, str);
    int size = num_of_words(str);
    string* arr = arr_of_words(str, size);

    int k;
    cout << "Input k: ";
    cin >> k;
    int new_size = find_new_size(arr, size, k);
    string* new_arr = arr_of_words_less_than_k(arr, size, k);

    cout << "\nWords less than k: " << endl;
    output_arr(new_arr, new_size);

    delete[] arr;
    return 0;
}


int num_of_words(string str) {
    int count = 1;
    for (int i = 0; i < str.length(); ++i) {
        if (str[i] == ' ') {
            count++;
        }
    }
    return count;
}

string* arr_of_words(string str, int size) {
    string* arr = new string[size];
    int index = 0;
    string word;
    for (int i = 0; i < str.length(); ++i) {
        if (str[i] != ' ') {
            word += str[i];
        }
        else {
            arr[index] = word;
            word = ""; //очищаємо
            index++;
        }
    }
    arr[index] = word; //останнє слово
    return arr;
}

int find_new_size(string* arr, int size, int k) {
    int new_size = 0;
    for (int i = 0; i < size; ++i) {
        if (arr[i].length() < k) {
            new_size++;
        }
    }
    return new_size;
}


string* arr_of_words_less_than_k(string* arr, int size, int k) {
    int new_size = find_new_size(arr, size, k);
    string* new_arr = new string[new_size];
    int index = 0;
    for (int i = 0; i < size; ++i) {
        if (arr[i].length() < k) {
            new_arr[index] = arr[i];
            index++;
        }
    }
    return new_arr;
}

void output_arr(string* arr, int size) {
    for (int i = 0; i < size; ++i) {
        cout << arr[i] << " ";
    }
    cout << endl;
}