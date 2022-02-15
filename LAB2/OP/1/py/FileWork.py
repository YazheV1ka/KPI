import os
from os.path import getctime
from time import ctime


# запис у файл тексту з перевіркою на комбінацію клавіш
def write_file(str, file):
    i = 0
    while i < len(str):
        if str[i] == chr(17):
            str = str[:i:]  # Робимо зріз до символу "Ctrl" + "Q"
            if (len(str) != 0):
                file.write(str + '\n')
            return 0
        i += 1
    file.write(str + '\n')
    return 1


# введення тексту
def input_text(name):
    print("\nInput text (Press ctrl+q to end entering): ")
    bo = 1
    file = open(name, "w")
    while (bo):
        str_text = input()
        bo = write_file(str_text, file)
    file.close()


# запис рядків в список
def get_text():
    file = open("file1.txt", "r")
    return file.readlines()


# ввод слова
def input_word():
    print("\nEnter word: ")
    return input()


# знаходження слова у рядках
def find_words(find_word, text: list[str]):
    lst = []
    for line in text:
        words = line.split()
        for word in words:
            if find_word == word.replace('\r', ''):
                lst.append(line)
                break
    return lst


# читання файлу
def read_file(name):
    print(f"\nFile {name}:")
    file = open(name, "r")
    for i in file.readlines():
        print(i, end='')


def create_file(file_name, text: list[str]):
    file = open(file_name, 'w')
    file.writelines(text)
    file.close()


def get_file_info(name):

    print(f"\nFile2 info: \nsize: {os.path.getsize(name)}, creation data&time: {ctime(getctime(name))}")
