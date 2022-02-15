using System;
using System.Collections.Generic;

namespace csharp_lab1
{
    public class TextWorker
    {
        public string GetWord()
        {
            Console.CursorLeft = 0;
            Console.WriteLine("Enter word: ");
            string word = Console.ReadLine();
            return word;
        }

        public string[] CheckWord(string[] text, string wordToFind)
        {
            var list = new List<string>();
            foreach (var line in text)
            {
                string[] words = line.Replace("\r", "").Split(' ', StringSplitOptions.RemoveEmptyEntries);
                foreach (var word in words)
                {
                    if (wordToFind == word)
                    {
                        list.Add(line);
                        break;
                    }
                }
            }

            return list.ToArray();
        }
    }
}