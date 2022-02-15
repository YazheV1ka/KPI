from FileWork import *


def main():
    input_text("text.txt")
    text_list = get_text()
    word = input_word()
    read_file("text.txt")
    create_file("result.txt", find_words(word, text_list))
    read_file("result.txt")
    get_file_info("result.txt")


main()