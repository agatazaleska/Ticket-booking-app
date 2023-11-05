SERVER_URL="http://localhost:8080/"
GET_SCREENING_URL="${SERVER_URL}screening_api/screenings"
SCREENING_PARAMS="?date=2023-11-30&earliestTime=10:00&latestTime=20:00&sort=movieTitle,asc&sort=startTime,asc"

POST_RESERVAION_URL="${SERVER_URL}reservation_api/reservations"

GET_ROOM_URL="${SERVER_URL}room_api/rooms"

GET_SEAT_AVEILABILITY_URL="${SERVER_URL}seat_availability_api/seat_availabilities"


echo "------------------------------------------------------------------"
echo "Screening endpoint"
echo "------------------------------------------------------------------"

echo "Getting screenings from november 30th from 10:00 to 20:00"
echo "Sending get request to endpoint ${GET_SCREENING_URL}${SCREENING_PARAMS}"
curl "${GET_SCREENING_URL}${SCREENING_PARAMS}"
echo -e "\n"

echo "Getting screening with id=5 from endpoint ${GET_SCREENING_URL}/5"
curl "${GET_SCREENING_URL}/5"
echo -e "\n\n"


echo "------------------------------------------------------------------"
echo "Reservation endpoint"
echo "------------------------------------------------------------------"

# Posting a sample reservation
echo "Posting reservation request for screening with id=5 to endpoint ${POST_RESERVAION_URL}"
echo "Request body:"
echo '{
  "screeningId": 5,
  "requestedTickets": [
    {
      "seatRow": "C",
      "seatNumber": 1,
      "ticketType": "ADULT"
    },
    {
      "seatRow": "C",
      "seatNumber": 2,
      "ticketType": "CHILD"
    }
  ],
  "customerData": {
    "name": "John",
    "surname": "Doe"
  }
}'
curl -X POST "${POST_RESERVAION_URL}" -H "Content-Type: application/json" \
-d '{
  "screeningId": 5,
  "requestedTickets": [
    {
      "seatRow": "C",
      "seatNumber": 1,
      "ticketType": "ADULT"
    },
    {
      "seatRow": "C",
      "seatNumber": 2,
      "ticketType": "CHILD"
    }
  ],
  "customerData": {
    "name": "John",
    "surname": "Doe"
  }
}'
echo -e "\n"

# Invalid reservation - no seats in the reservation
echo "Posting reservation request for screening with id=5 to endpoint ${POST_RESERVAION_URL}"
echo "Request body:"
echo '{
  "screeningId": 4,
  "requestedTickets": [],
  "customerData": {
    "name": "John",
    "surname": "Doe"
  }
}'
curl -X POST "${POST_RESERVAION_URL}" -H "Content-Type: application/json" \
-d '{
  "screeningId": 4,
  "requestedTickets": [],
  "customerData": {
    "name": "John",
    "surname": "Doe"
  }
}'
echo -e "\n"

# Invalid reservation - isolated seat in a row left
echo "Posting reservation request for screening with id=5 to endpoint ${POST_RESERVAION_URL}"
echo "Request body:"
echo '{
  "screeningId": 5,
  "requestedTickets": [
    {
      "seatRow": "C",
      "seatNumber": 4,
      "ticketType": "STUDENT"
    }
  ],
  "customerData": {
    "name": "John",
    "surname": "Doe"
  }
}'
curl -X POST "${POST_RESERVAION_URL}" -H "Content-Type: application/json" \
-d '{
  "screeningId": 5,
  "requestedTickets": [
    {
      "seatRow": "C",
      "seatNumber": 4,
      "ticketType": "STUDENT"
    }
  ],
  "customerData": {
    "name": "John",
    "surname": "Doe"
  }
}'
echo -e "\n"

# Invalid reservation - invalid customer data
echo "Posting reservation request for screening with id=5 to endpoint ${POST_RESERVAION_URL}"
echo "Request body:"
echo '{
  "screeningId": 4,
  "requestedTickets": [
    {
      "seatRow": "A",
      "seatNumber": 3,
      "ticketType": "ADULT"
    }
  ],
  "customerData": {
    "name": "John1",
    "surname": "Doe"
  }
}'
curl -X POST "${POST_RESERVAION_URL}" -H "Content-Type: application/json" \
-d '{
  "screeningId": 4,
  "requestedTickets": [
    {
      "seatRow": "A",
      "seatNumber": 3,
      "ticketType": "ADULT"
    }
  ],
  "customerData": {
    "name": "John1",
    "surname": "Doe"
  }
}'
echo -e "\n\n"


echo "------------------------------------------------------------------"
echo "Room endpoint"
echo "------------------------------------------------------------------"

echo "Getting information about screening 5's room"
echo "Sending get request to endpoint ${GET_ROOM_URL}/5"
curl "${GET_ROOM_URL}/5"
echo -e "\n"

echo "Getting information about screening 6's room"
echo "Sending get request to endpoint ${GET_ROOM_URL}/6"
curl "${GET_ROOM_URL}/6"
echo -e "\n"


echo "Getting information about screening 7's room"
echo "Sending get request to endpoint ${GET_ROOM_URL}/7"
curl "${GET_ROOM_URL}/7"
echo -e "\n\n"


echo "------------------------------------------------------------------"
echo "Seat availability endpoint"
echo "------------------------------------------------------------------"

echo "Getting information about seat availability in screening 5's room"
echo "Sending get request to endpoint ${GET_SEAT_AVEILABILITY_URL}/5"
curl "${GET_SEAT_AVEILABILITY_URL}/5"
echo -e "\n"

echo "Getting information about seat availability in screening 6's room"
echo "Sending get request to endpoint ${GET_SEAT_AVEILABILITY_URL}/6"
curl "${GET_SEAT_AVEILABILITY_URL}/6"
echo -e "\n"


echo "Getting information about seat availability in screening 7's room"
echo "Sending get request to endpoint ${GET_SEAT_AVEILABILITY_URL}/7"
curl "${GET_SEAT_AVEILABILITY_URL}/7"
echo -e "\n\n"


