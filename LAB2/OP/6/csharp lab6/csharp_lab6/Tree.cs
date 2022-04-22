using System;

namespace csharp_lab6
{
    public class Tree
    {
        private Node _root;
        public Node Root { get => _root; set => _root = value; }
        public Node AddNode(int inputDataNode, Node root)
        {
            if (root == null)
            {
                root = new Node(inputDataNode);
            }
            else
            {
                if (inputDataNode < root.Data)
                {
                    root.Left = AddNode(inputDataNode, root.Left);
                }
                else
                {
                    root.Right = AddNode(inputDataNode, root.Right);
                }
            }
 
            return root;
        }

        public void PrintTree(int x, int y, Node root, int delta = 0)
        {
            if (root != null)
            {
                if (delta == 0) delta = x/3;
                Console.SetCursorPosition(x, y);
                Console.Write(root.Data);
                PrintTree(x-delta, y + 2, root.Left, delta / 3);
                PrintTree(x+delta, y + 2, root.Right,delta / 3);
            }
            
        }
        public int SummaElements(Node root)
        {
            if (root == null)
                return 0;
            else
            {
                int count = 0;
                count += SummaElements(root.Left);
                count += SummaElements(root.Right);
 
                return count + root.Data;
            }
        }
    }
}
