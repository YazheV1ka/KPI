internal struct Info
{
    public string Destination;
    public (int Hour, int Minute) Departure;
    public (int Hour, int Minute) Arrival;

    public Info(string dist, (int, int) depart, (int, int) arriv)
    {
        Destination = dist;
        Departure = depart;
        Arrival = arriv;
    }
}