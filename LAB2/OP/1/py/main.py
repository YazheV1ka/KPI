from FileWork import *


def main():
    input_text("file1.txt")
    text_list = get_text()
    word = input_word()
    read_file("file1.txt")
    create_file("file2.txt", find_words(word, text_list))
    read_file("file2.txt")
    get_file_info("file2.txt")


main()