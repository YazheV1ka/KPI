from itertools import count

from Trips import Trips
import pickle


def get_info(name):
    with open(name, "rb") as file_read:
        text = pickle.load(file_read)
    return text


def read_file(name):
    file_read = open(name, "rb")
    text = pickle.load(file_read)
    for trip in text:
        trip.write()


def create_file(name):
    write_file = open(name, "wb")
    count = int(input("Enter number of trips: "))
    text = []
    #if count >= 3 and count <=5:
    for i in range(count):
        trips = Trips()
        trips.number = str(i + 1)
        print(f"\nTrip number: {trips.number}")
        trip_dest = str(input("Destination: "))
        trips.destination = trip_dest
        dep_time = str(input("Departure time (HH:MM): "))
        if dep_time[:2] < "0" or dep_time[:2] > "24" or dep_time[3:] < "0" or dep_time[3:] > "59":
            print("\n!!!Wrong time!!!")
            exit(0)
        else:
            trips.departure = dep_time
        arrival_time = str(input("Arrival time (HH:MM): "))
        if arrival_time[:2] < "0" or arrival_time[:2] > "24" or arrival_time[3:] < "0" or arrival_time[3:] > "59":
            print("\n!!!Wrong time!!!")
            exit(0)
        else:
            trips.arrival = arrival_time
        text.append(trips)
    pickle.dump(text, write_file)


def winter_schedule(name1, name2):
    file_read = open(name1, "rb")
    file_write = open(name2, "wb")

    text_read = pickle.load(file_read)
    text_write = []
    for trip in text_read:
        if trip.departure < "10" or trip.departure > "18":
            text_write.append(trip)
    pickle.dump(text_write, file_write)
