package com.example.sbtickets.bean;

import com.example.sbtickets.entity.TripBus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ObjectByTripBus {
    private TripBus tripBus;
    private List<Integer> bookedSeat;

    public TripBus getTripBus() {
        return tripBus;
    }

    public void setTripBus(TripBus tripBus) {
        this.tripBus = tripBus;
    }

    public List<Integer> getBookedSeat() {
        return bookedSeat;
    }

    public void setBookedSeat(List<Integer> bookedSeat) {
        this.bookedSeat = bookedSeat;
    }

    public ObjectByTripBus(TripBus tripBus, List<Integer> bookedSeat) {
        this.tripBus = tripBus;
        this.bookedSeat = bookedSeat;
    }
}
