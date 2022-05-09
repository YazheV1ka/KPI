using System;

namespace csharp_lab5
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Enter number of pyramids: ");
            int n = Convert.ToInt32(Console.ReadLine());
            Console.WriteLine("Enter number of cylinders: ");
            int m = Convert.ToInt32(Console.ReadLine());

            TPyramid[] pyr = new TPyramid[n];
            TCylinder[] cyl = new TCylinder[m];

            double[] Square = new double[n];
            double[] Volume = new double[m];

            for (int i = 0; i < Square.Length; i++)
            {
                pyr[i] = new TPyramid(Rnd(), Rnd());
                Square[i] = pyr[i].S();
                Console.WriteLine(pyr[i]);
            }
            Console.WriteLine("\n");
            for (int i = 0; i < Volume.Length; i++)
            {
                cyl[i] = new TCylinder(Rnd(), Rnd());
                Volume[i] = cyl[i].V();
                Console.WriteLine(cyl[i]);
            }

            Console.WriteLine("\n");
            
            double min = minS(Square);
            double max = maxV(Volume);
            Console.WriteLine($"Minimum Pyramid Square = {Math.Round(min, 3)}");
            Console.WriteLine($"Maximum Cylinder Volume = {Math.Round(max, 3)}");

        }
        
        public static double minS(double[] arr)
        {
            double minS = arr[0];
            for (int i = 0; i < arr.Length; i++)
            {
                if (arr[i] < minS) minS = arr[i];
            }
            return minS;
        }
        public static double maxV(double[] arr)
        {
            double maxV = arr[0];
            for (int i = 0; i < arr.Length; i++)
            {
                if (arr[i] > maxV) maxV = arr[i];
            }
            return maxV;
        }
        public static int Rnd()
        {
            Random rnd = new Random();
            return rnd.Next(1, 21);
        }
    }
}