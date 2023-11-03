package com.example.demo.util;

import com.example.demo.entity.Seat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ReservationRequest {
    private static final int acceptableMinutesBeforeScreening = 15;
    private int screeningId;
    private List<TicketRequest> requestedTickets;
    private CustomerData customerData;

    public ReservationRequest(int screeningId, List<TicketRequest> requestedTickets, CustomerData customerData) {
        this.screeningId = screeningId;
        this.requestedTickets = requestedTickets;
        this.customerData = customerData;
    }

    public boolean isEarlyEnough(LocalDateTime screeningTime) {
        return LocalDateTime.now()
                            .plusMinutes(acceptableMinutesBeforeScreening)
                            .isBefore(screeningTime);
    }

    public float getCost(Map<TicketType, Float> prices) {
        float totalCost = 0;
        for (TicketRequest ticketRequest : requestedTickets) {
            totalCost += prices.get(ticketRequest.getTicketType());
        }
        return totalCost;
    }

    public boolean leavesIsolatedSeat(RoomLayout roomLayout,
                                      Map<Integer, Boolean> seatIdToAvailability) {
        return leavesIsolatedRightSeat(roomLayout, seatIdToAvailability)
                || leavesIsolatedLeftSeat(roomLayout, seatIdToAvailability);
    }

    private boolean leavesIsolatedRightSeat(RoomLayout roomLayout,
                                           Map<Integer, Boolean> seatIdToAvailability) {
        for (TicketRequest ticketRequest : requestedTickets) {
            int seatNumber = ticketRequest.getSeatNumber();
            char seatRow = ticketRequest.getSeatRow();
            Optional<Seat> toTheRight = roomLayout.SeatToTheRight(seatRow, seatNumber);
            if (toTheRight.isEmpty()) {
                continue;
            }

            Seat rightSeat = toTheRight.get();
            if (seatNotInReservationAndFree(rightSeat, seatIdToAvailability)) {
                Optional<Seat> twoToTheRight = roomLayout.
                        SeatToTheRight(rightSeat.getRow(), rightSeat.getSeatNumber());
                if (twoToTheRight.isEmpty()) {
                    continue;
                }
                if (seatInReservationOrTaken(twoToTheRight.get(), seatIdToAvailability)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean leavesIsolatedLeftSeat(RoomLayout roomLayout,
                                          Map<Integer, Boolean> seatIdToAvailability) {
        for (TicketRequest ticketRequest : requestedTickets) {
            int seatNumber = ticketRequest.getSeatNumber();
            char seatRow = ticketRequest.getSeatRow();
            Optional<Seat> toTheLeft = roomLayout.SeatToTheLeft(seatRow, seatNumber);
            if (toTheLeft.isEmpty()) {
                continue;
            }

            Seat leftSeat = toTheLeft.get();
            if (seatNotInReservationAndFree(leftSeat, seatIdToAvailability)) {
                Optional<Seat> twoToTheLeft = roomLayout.
                        SeatToTheLeft(leftSeat.getRow(), leftSeat.getSeatNumber());
                if (twoToTheLeft.isEmpty()) {
                    continue;
                }
                if (seatInReservationOrTaken(twoToTheLeft.get(), seatIdToAvailability)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean seatInReservationOrTaken(Seat seat, Map<Integer,
                                            Boolean> seatIdToAvailability) {
        return containsSeat(seat.getRow(), seat.getSeatNumber())
                || !seatIdToAvailability.get(seat.getId());
    }

    public boolean seatNotInReservationAndFree(Seat seat, Map<Integer,
                                               Boolean> seatIdToAvailability) {
        return !containsSeat(seat.getRow(), seat.getSeatNumber())
                && seatIdToAvailability.get(seat.getId());
    }

    public boolean containsSeat(char row, int seatNumber) {
        for (TicketRequest ticketRequest : requestedTickets) {
            if (row == ticketRequest.getSeatRow()
                && seatNumber == ticketRequest.getSeatNumber()) {
                return true;
            }
        }
        return false;
    }

    public int getScreeningId() {
        return screeningId;
    }

    public List<TicketRequest> getRequestedTickets() {
        return requestedTickets;
    }

    public CustomerData getCustomerData() {
        return customerData;
    }
}
