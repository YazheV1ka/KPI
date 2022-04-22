using System;

namespace csharp_lab4
{
    class Program
    {
        static void Main(string[] args)
        {
            Triangle[] triangles = new Triangle[3];
            triangles[0] = new Triangle(
                (Rnd(),Rnd(),Rnd()),
                (Rnd(),Rnd(),Rnd()),
                (Rnd(),Rnd(),Rnd()));
            triangles[1] = new Triangle(
                (Rnd(), Rnd(), Rnd()),
                (Rnd(),Rnd(),Rnd()));
            triangles[2] = new Triangle(
                 (Rnd(), Rnd(),Rnd()));

            for (int i = 0; i < triangles.Length; i++)
            {
                Console.WriteLine($"Triangle {i+1}:");
                triangles[i].Print();
            }

            Console.WriteLine("\n");
            
            Console.WriteLine("Enter the value to increase coordinates: ");
            int value = Convert.ToInt32(Console.ReadLine());
            
            Console.WriteLine("\n");

            Triangle T1 = +triangles[0];
            Triangle T2 = ++triangles[1];
            Triangle T3 = triangles[2] + value;

            Console.WriteLine("Changed Triangle 1:");
            T1.Print();
            Console.WriteLine("Changed Triangle 2:");
            T2.Print();
            Console.WriteLine("Changed Triangle 3:");
            T3.Print();
            
            Console.WriteLine("\n");

            double P1 = triangles[0].Perimeter();
            double P2 = triangles[1].Perimeter();
            double P3 = triangles[2].Perimeter();
            
            double maxP = Math.Max(P1, Math.Max(P2, P3));
            Console.WriteLine($"P1: {P1}");
            Console.WriteLine($"P2: {P2}");
            Console.WriteLine($"P3: {P3}");
            Console.WriteLine($"Max P: {maxP}");
        }
        public static int Rnd()
        {
            Random rnd = new Random();
            return rnd.Next(0, 21);
        }
    }
}
