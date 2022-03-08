using System;


namespace csharp_lab3
{
    class Program
    {
        static void Main()
        {
            Console.WriteLine("Enter number of texts:");
            var numOfText = Int32.Parse(Console.ReadLine());
            
            TextWorker[] text = new TextWorker[numOfText];

            for (int i = 0; i < numOfText; i++)
            {
                text[i] = new TextWorker(i);
            }
            Console.WriteLine();

            Console.WriteLine("Choose index of text to add string:");
            var indOfText = Int32.Parse(Console.ReadLine());
            Console.WriteLine("Add string:");
            text[indOfText-1].InputText(Console.ReadLine());
            Console.WriteLine();


            for (int i = 0; i < numOfText; i++)
            { 
                Console.WriteLine($"Text {i+1}:");
                text[i].OutputText();
                Console.WriteLine($"Persent of symbols/numbers: {text[i].CheckPersent()}");
                Console.WriteLine();
            }
            

            double minPersent = text[0].CheckPersent();
            int minInd = 0;
            
            for (int i = 0; i < numOfText; i++)
            {
                if (minPersent > text[i].CheckPersent())
                {
                    minPersent = text[i].CheckPersent();
                    minInd = i;
                }
            }
            Console.WriteLine($"Text with smallest persent {minInd+1}");
        }
    }
}
