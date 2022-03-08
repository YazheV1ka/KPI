using System;
using System.Collections.Generic;


namespace csharp_lab3
{
    public class TextWorker
    {
        List<string> text = new List<string>();
        string str = string.Empty;

        public TextWorker(int i)
        {
            Console.WriteLine($"Enter string to text {i+1}:");
            text.Add(Console.ReadLine());
        }

        
        public void InputText(string str)
        {
            text.Add(str);
            str = string.Empty;
        }

        public double CheckPersent()
        {
            double alpha = 0;
            double digit = 0;

            foreach (var line in text)
            {
                foreach (char symb in line)
                {
                    if (Char.IsNumber(symb))
                    {
                        digit++;
                    }
                    else
                    {
                        alpha++;
                    }

                }
            }

            if (digit == 0)
            {
                return 100;
            }
            else if(alpha == 0)
            {
                return 0;
            }

            return (alpha / digit) * 100;
        }
            
        public void OutputText()
        {
            foreach (var line in text)
            {
                Console.WriteLine(line);
            }
            
        }
    }
}