using System;
namespace csharp_lab5
{
    internal class TCylinder : TFigure
    {
        private double r, h;
        public TCylinder(double r, double h)
        {
            this.r = r;
            this.h = h;
        }
        public double Vc(double r, double h)
        {
            double res = pi * h * Math.Pow(r, 2);
            return res;
        }
        
        public double getR(){ return r; }
        public double getH(){ return h; }
        
        public string ToStr()
        {
            return "Cylinder: " +
                   $"radius = {r} " +
                   $"height = {h} " +
                   $"Volume = {Math.Round(Vc(r, h),3)}";
        }
    }
}