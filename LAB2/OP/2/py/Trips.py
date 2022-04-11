class Trips:
    def __init__(self, number="", destination="", departure="", arrival=""):
        self.number = number
        self.destination = destination
        self.departure = departure
        self.arrival = arrival

    def write(self):
        print("Trip number: " + self.number +
              "\nDestination: " + self.destination +
              "\nDeparture time: " + self.departure +
              "\nArrival time: " + self.arrival + "\n")