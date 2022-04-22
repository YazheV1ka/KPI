using System;

namespace csharp_lab4
{
    public class Triangle
    {
        public (int x,int y ,int z) A {get; set;}
        public (int x,int y ,int z) B {get; set;}
        public (int x,int y ,int z) C {get; set;}

        
        public Triangle((int x, int y, int z) a, (int x, int y, int z) b, (int x, int y, int z) c)
        {
             A = a;
             B = b;
             C = c;
        }
        
        public Triangle((int x, int y, int z) a, (int x, int y, int z) b)
        {
            A = a;
            B = b;
            C = (0, 0, 0);
        }
        
        public Triangle((int x, int y, int z) a)
        {
            A = a;
            B = (0, 0, 0);
            C = (0, 0, 0);
        }
        
        public void Print()
        {
            string[] triangle = {A.ToString(),B.ToString(),C.ToString()};
            for (int i = 0; i < triangle.Length; i++)
            {
                if (triangle[i].Length == 1)
                {
                    triangle[i] = "0" + triangle[i];
                }
            }
            Console.WriteLine($"{triangle[0]},{triangle[1]},{triangle[2]}");
        }

        public static Triangle operator ++(Triangle name)
        {
            var A = (name.A.x, (name.A.y) +1, name.A.z);
            var B = (name.B.x, (name.B.y) +1, name.B.z);
            var C = (name.C.x, (name.C.y) +1, name.C.z);
            return new Triangle(A, B, C);
        }

        public static Triangle operator +(Triangle name)
        {
            var A = (1 + name.A.x, name.A.y, name.A.z);
            var B = (1 + name.B.x, name.B.y, name.B.z);
            var C = (1 + name.C.x, name.C.y, name.C.z);
            
            return new Triangle(A,B,C);
        }
        
        public static Triangle operator +(Triangle name, int value)
        {
            var A = (name.A.x + value, name.A.y + value, name.A.z + value);
            var B = (name.B.x + value, name.B.y + value, name.B.z + value);
            var C = (name.C.x + value, name.C.y + value, name.C.z + value);
            
            return new Triangle(A,B,C);
        }
        
        public double Perimeter()
        {
            double ab = Math.Sqrt(Math.Pow((B.x - A.x),2) + Math.Pow((B.y - A.y),2) + Math.Pow((B.z - A.z),2));
            double bc = Math.Sqrt(Math.Pow((C.x - B.x),2) + Math.Pow((C.y - B.y),2) + Math.Pow((C.z - B.z),2));
            double ac = Math.Sqrt(Math.Pow((C.x - A.x),2) + Math.Pow((C.y - C.y),2) + Math.Pow((C.z - A.z),2));
            double res = ab + bc + ac;
            return Math.Round(res,3);
        }

    }
}