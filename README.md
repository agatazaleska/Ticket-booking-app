# Ticket-booking-app
This is backend for ticket booking app - a recruitment task for Touk.
I do not own the idea for this project.

The ticket booking app enables the client to:

- get the screenings at the given date and time interval,
- get information about a specific screening,
- get information about screening's room and available seats,
- request a reservation after providing his data,
- get information about the reservation: total cost and expiration time.

The application handles multiple rooms.



### Additional assumptions

Additionally, the system checks the following conditions:

- a reservation cannot be made later then 15 minutes before the screening start,
- client's data (name and surname) has to be in correct format,
- there cannot be an empty reservation,
- there cannot be a single place left over in a row between two already reserved
  places.

The application handles polish characters properly.



### Building and running

##### Prerequisites

Before running the project be sure that you have installed:

1. Docker
2. Java Development Kit (JDK) (Version 17)
3. Git (for cloning the repository)

##### Building and running instructions

To build and run the application:

1.  Clone the repo
2.  Navigate to `[PATH_TO_REPO]/scripts`
3.  Modify script permissions by entering `chmod +x build-and-run.sh`
4.  Launch the script `build-and-run.sh`

The script initializes the docker MySQL container and fills the database with some sample data.
Then it builds and runs the application.

To gain access to MySQL session, launch `scripts/sql-docker-session.sh`



### Use cases

**The user selects the day and the time when he/she would like to see the movie.**

The user can retrieve screenings scheduled for a specific date and time range, along with sorting preferences, 
by sending a GET request to an endpoint with the relevant query parameters. 
For example, sending a GET request to

`http://localhost:8080/screening_api/screenings?date=2023-11-30&earliestTime=10:00&latestTime=20:00&sort=movieTitle,asc&sort=startTime,asc`

results in the system listing the available screenings on November 30th between 10 AM and 8 PM 
sorted by movie title and then start time.



**The user chooses a particular screening.**

The user can retrieve information about a particular screening by sending a get request to an endpoint
with the screening id provided in the path. For example, sending a GET request to

`http://localhost:8080/screening_api/screenings/5`

results in the system providing basic information about the chosen screening.



**The system gives information regarding screening room and available seats.**

Furthermore, the user can retrieve information about the room in which the chosen screening is played
and the seats availability for this screening. For example, sending a GET request to

`http://localhost:8080/room_api/rooms/3`

results in getting some basic information about the room with id=3 is played in.
Sending a GET request to

`http://localhost:8080/seat_availability_api/seat_availabilities/5`

results in getting information about seat availability for this screening.



**The user chooses seats, and gives the name of the person doing the reservation (name and surname).**

The user can make a reservation by sending a POST request to

`http://localhost:8080/reservation_api/reservation/5`

The user provides: requested screening id, requested seats (seat row + seat number + ticket type)
and customer data (name + surname).

The system processes the reservation.



**The system gives back the total amount to pay and reservation expiration time.**

If the reservation can be made (seats are available, user data is correct, etc) the system responds with:
Reservation and screening id, customer data, total cost, expiration time.



### Use case demo

The use case demo presents the application's key functionalities:

- retrieving screenings for a specific date and time range,
- attempting reservations with various scenarios, highlighting both successful and failed bookings due to constraints like seat availability and customer data validation,
- fetching room details associated with particular screenings,
- checking seat availability for specific screenings, providing a snapshot of current bookings.

The provided script utilizes cURL to access the endpoints.
It also resets the data in the database.

**Make sure to run the use case demo while the app is running!**

To launch the use case demo:

1.  Navigate to `[PATH_TO_REPO]/scripts`
2. Modify script permissions by entering `chmod +x use-case-demo.sh`
3. Launch the script `scripts/use-case-demo.sh`
