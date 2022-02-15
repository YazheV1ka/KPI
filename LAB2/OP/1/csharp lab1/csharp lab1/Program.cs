using System;
using System.IO;

namespace csharp_lab1
{
    class Program
    {
        static void Main(string[] args)
        {
            TextWorker textWorker = new TextWorker();
            FileWorker fileWorker = new FileWorker();
            
            string path1 = @"D:\code\C#\Лабы\first\csharp lab1\files\1";
            string path2 = @"D:\code\C#\Лабы\first\csharp lab1\files\2";
            
            fileWorker.CreateFile1(path1);
            
            string word = textWorker.GetWord();
            
            Console.WriteLine("\nText File1: ");
            fileWorker.OutputFromFile(path1);
            string[] text = fileWorker.ReadFile(path1);
            
            string[] newText = textWorker.CheckWord(text, word);
            
            FileInfo file2 = fileWorker.CreateFile2(path2, newText);
            Console.WriteLine("Text File2: ");
            fileWorker.OutputFromFile(path2);
            
            Console.WriteLine("\nInfo File2: ");
            fileWorker.PrintInfo(file2);

            Console.ReadLine();
        }
    }
}