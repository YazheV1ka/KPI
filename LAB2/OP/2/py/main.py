from FileWorker import*

def main():
    path1 = "D:\\code\\C#\\Лабы\\second\\py\\1";
    path2 = "D:\\code\\C#\\Лабы\\second\\py\\2";

    create_file(path1)
    print("\nTrips: ")
    read_file(path1)
    print("Winter Schedule: ")

    winter_schedule(path1, path2)
    read_file(path2)


main()
