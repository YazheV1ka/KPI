using System;
namespace csharp_lab5
{
    internal class TPyramid : TFigure
    {
        private double a, p, h;
        public TPyramid(double a, double p)
        {
            this.a = a;
            this.p = p;
        }
        public override double S()
        {
            return (p * a) / 2;
        }
        
        public override double V()
        {
            return (p * a) / 2 * (1/3)  * h;
        }

        public double A { get => a; set => a = value;}
        public double P { get => p; set => p = value;}
        
       public override string ToString()
       {
           return "Pyramid: " +
                  $"apothem = {a} " +
                  $"perimeter = {p} " +
                  $"Square = {Math.Round(S(),3)}";
       }
    }
}