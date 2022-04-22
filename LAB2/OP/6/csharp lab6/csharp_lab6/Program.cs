using System;

namespace csharp_lab6
{
    class Program
    {
        static void Main(string[] args)
        {
            Tree tree = new Tree();
            
            Console.WriteLine("Enter number of elements in tree: ");
            int numOfEl = Convert.ToInt32(Console.ReadLine());

            Console.WriteLine("Tree: ");
            for (int i = 0; i < numOfEl; i++)
            {
                tree.Root = tree.AddNode(Rnd(), tree.Root);
                tree.PrintTree(Console.WindowWidth / 3, 3, tree.Root);
            }
            

            Console.SetCursorPosition(0, 25);
            Console.WriteLine($"Average of elements in tree: {(tree.SummaElements(tree.Root))/numOfEl+1}");
            Console.ReadKey();

        }
        
        public static int Rnd()
        {
            Random rnd = new Random();
            return rnd.Next(0, 21);
        }
    }
}