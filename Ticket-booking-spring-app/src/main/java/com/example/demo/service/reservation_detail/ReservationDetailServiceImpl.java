package com.example.demo.service.reservation_detail;

import com.example.demo.dao.ReservationDetailRepository;
import com.example.demo.entity.ReservationDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationDetailServiceImpl implements ReservationDetailService {
    private ReservationDetailRepository reservationDetailRepository;

    @Autowired
    ReservationDetailServiceImpl(ReservationDetailRepository theReservationDetailRepository) {
        reservationDetailRepository = theReservationDetailRepository;
    }

    @Override
    public ReservationDetail save(ReservationDetail reservationDetail) {
        return reservationDetailRepository.save(reservationDetail);
    }
}
