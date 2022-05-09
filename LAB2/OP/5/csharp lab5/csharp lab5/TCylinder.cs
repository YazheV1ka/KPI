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
        
        public override double V()
        {
            return Math.PI * h * Math.Pow(r, 2);
        }
        public override double S()
        {
            return 2 * Math.PI * r * (h + r);
        }
        
        public double R { get => r; set => r = value;}
        public double H{ get => h; set => h = value;}
        
        public override string ToString()
        {
            return "Cylinder: " +
                   $"radius = {r} " +
                   $"height = {h} " +
                   $"Volume = {Math.Round(V(),3)}";
        }
    }
}