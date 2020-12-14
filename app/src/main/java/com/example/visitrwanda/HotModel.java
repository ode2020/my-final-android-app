package com.example.visitrwanda;

import java.io.Serializable;

public class HotModel implements Serializable {
    private String hotelName;

    public HotModel(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
}
