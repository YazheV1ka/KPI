using System;
namespace csharp_lab5
{
    internal class TPyramid : TFigure
    {
        private double a, p;
        public TPyramid(double a, double p)
        {
            this.a = a;
            this.p = p;
        }
        public double Sp(double a, double p)
        {
            double res =  (p * a) / 2;
            return res;
        }
        public double getA(){ return a; }
        public double getP(){ return p; }
        
       public string ToStr()
       {
           return "Pyramid: " +
                  $"apothem = {a} " +
                  $"perimeter = {p} " +
                  $"Square = {Math.Round(Sp(a, p),3)}";
       }
    }
}