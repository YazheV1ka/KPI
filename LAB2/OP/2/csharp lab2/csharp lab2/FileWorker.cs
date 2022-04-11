using System;
using System.Collections.Generic;
using System.Text;
using System.IO;
using csharp_lab2;

internal class FileWorker
    {
        public void CreateFile(string path, string text) //створення файлу
        {
            FileStream fileStream = new FileStream(path, FileMode.Create);
            byte[] arr = Encoding.Default.GetBytes(text);
            fileStream.Write(arr, 0, arr.Length);
            fileStream.Close();
        }

        public string ReadFile(string path) //зчитування з файлу
        {
            FileStream fileStream = new FileStream(path, FileMode.Open);
            byte[] arr = new byte[fileStream.Length];
            fileStream.Read(arr, 0, arr.Length);
            string textFromFile = Encoding.Default.GetString(arr);
            fileStream.Close();
            return textFromFile;
        }

        public Info[] StringToInfo(string path) // текст файлу
        {
            string[] fromFile = ReadFile(path).Split("\n", StringSplitOptions.RemoveEmptyEntries);
            List<Info> list = new List<Info>();
            string dest = "";
            (int, int) depart = (0, 0), arrive = (0, 0);
            for (int i = 0; i < fromFile.Length; i++)
            {
                string[] splitted = fromFile[i].Split(':', StringSplitOptions.RemoveEmptyEntries);
                if ((i + 1) % 3 == 1)
                    dest = splitted[1];
                else if ((i + 1) % 3 == 2)
                    depart = (Convert.ToInt32(splitted[1]), Convert.ToInt32(splitted[2]));
                else
                {
                    arrive = (Convert.ToInt32(splitted[1]), Convert.ToInt32(splitted[2]));
                    list.Add(new Info(dest, depart, arrive));
                }
            }

            return list.ToArray();
        }

        public void OutputFile(string path) => Console.WriteLine(ReadFile(path)); //вивід з файлу

        public void AppendFile(string pathToFile, string text) //доповнення файлу
        {
            FileStream fileStream = new FileStream(pathToFile, FileMode.Append);
            byte[] arr = Encoding.Default.GetBytes(text + "\n");
            fileStream.Write(arr, 0, arr.Length);
            fileStream.Close();
        }
    }