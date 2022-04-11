using System;
using System.Collections.Generic;
using System.Linq;

namespace csharp_lab2
{
    internal class InfoWorker
    {
        public Info[] GetWinterTrips(Info[] wholeSchedule)
        {
            return wholeSchedule.Where(t => t.Departure.Hour < 10 || t.Departure.Hour > 18).ToArray();
        }
         public Info[] GetInput()
        {
            Console.Write("Enter number of trips:");
            int count = int.Parse(Console.ReadLine());
            List<Info> input = new List<Info>(count);
            //if (count >= 3 && count <= 5)
            {
                for (int i = 0; i < count; i++)
                {
                    Console.Write("\nDestination: ");
                    string destination = Console.ReadLine();
                    string[] depart, arriv;
                    do
                    {
                        Console.Write("Departure time (HH:MM): ");
                        depart = Console.ReadLine().Split(':');
                    } while (!(depart.Length == 2 && int.TryParse(depart[0], out _) && int.TryParse(depart[1], out _) &&
                               int.Parse(depart[0]) <= 24 && int.Parse(depart[1]) <= 59));

                    do
                    {
                        Console.Write("Arrival time(HH:MM): ");
                        arriv = Console.ReadLine().Split(':');
                    } while (!(arriv.Length == 2 && int.TryParse(arriv[0], out _) && int.TryParse(arriv[1], out _) &&
                               int.Parse(arriv[0]) <= 24 && int.Parse(arriv[1]) <= 59));

                    input.Add(new Info(destination, (int.Parse(depart[0]), int.Parse(depart[1])),
                        (int.Parse(arriv[0]), int.Parse(arriv[1]))));
                }
            }
            return input.ToArray();
        }

        public string InfoToString(Info inf)
        {
            return $"Destination:{inf.Destination}\n" +
                   $"Departure time:{string.Format("{0,0:D2}", inf.Departure.Hour)}:{string.Format("{0,0:D2}", inf.Departure.Minute)}\n" +
                   $"Arrival time:{string.Format("{0,0:D2}", inf.Arrival.Hour)}:{string.Format("{0,0:D2}", inf.Arrival.Minute)}\n";
        }

        public string InfoArrToString(Info[] inf)
        {
            string res = "";
            foreach (var i in inf)
            {
                res += InfoToString(i) + "\n";
            }

            return res;
        }
    }
}