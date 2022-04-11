using System;

namespace csharp_lab2
{
    internal class Program
    {
        static void Main(string[] args)
        {
            string path1 = @"file1";
            string path2 = @"file2";

            FileWorker fileWorker = new FileWorker();
            InfoWorker infoWorker = new InfoWorker();

            fileWorker.CreateFile(path1, infoWorker.InfoArrToString(infoWorker.GetInput()));
            Console.WriteLine("\nFile 1: ");
            fileWorker.OutputFile(path1);
            Info[] fromFile = fileWorker.StringToInfo(path1);
            var winterTrips = infoWorker.GetWinterTrips(fromFile);
            fileWorker.CreateFile(path2, infoWorker.InfoArrToString(winterTrips));

            Console.WriteLine("File 2:\t[WINTER SCHEDULE]");
            fileWorker.OutputFile(path2);


            Console.ReadLine();
            Console.ReadLine();
        }
    }

}