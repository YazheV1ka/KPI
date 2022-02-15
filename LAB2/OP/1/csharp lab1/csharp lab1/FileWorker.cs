using System.IO;
using System;
using System.Collections.Generic;

namespace csharp_lab1
{
    public class FileWorker
    {
        
        public void CreateFile1(string path)
        {
            
            if (!File.Exists(path))
            {
                File.Create(path);
            }

            StreamWriter streamWriter = new StreamWriter(path);
            string[] text = InputFile();

            foreach (var line in text)
            {
                streamWriter.WriteLine(line);
            }
            streamWriter.Close();
        }
        
        public string[] InputFile()
        {
            Console.WriteLine("\nInput text (Press ctrl+q to end entering): ");
            var text = new List<string>();
            string str = string.Empty;
            while (true)
            {
                ConsoleKeyInfo key = Console.ReadKey();
                
                if (key.Key == ConsoleKey.Q && key.Modifiers == ConsoleModifiers.Control)
                {
                    Console.CursorLeft--;
                    Console.Write(" ");
                    break;
                }
                str += key.KeyChar;
                str += Console.ReadLine();
                text.Add(str);
                str = string.Empty;
            }

            return text.ToArray();
        }

        public void OutputFromFile(string path)
        {
            StreamReader streamReader = new StreamReader(path);
            Console.WriteLine(streamReader.ReadToEnd());
            streamReader.Close();
        }

        public string[] ReadFile(string path)
        {
            StreamReader streamReader = new StreamReader(path);
            string[] text =  streamReader.ReadToEnd().Split("\n");
            streamReader.Close();
            return text;
        }

        public FileInfo CreateFile2(string path, string[] text)
        {
            FileInfo file = new FileInfo(path);
            StreamWriter streamWriter = new StreamWriter(path, append: false);
            foreach (var line in text)
            {
                streamWriter.WriteLine(line);
            }
            streamWriter.Close();
            return file;
        }

        public void PrintInfo(FileInfo file)
        {
            Console.WriteLine($"Creation data & time: {file.CreationTime}");
            Console.WriteLine($"Size of file: {file.Length}");
        }
    }
}