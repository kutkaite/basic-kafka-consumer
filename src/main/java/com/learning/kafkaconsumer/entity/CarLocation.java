package com.learning.kafkaconsumer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarLocation {

    @JsonProperty("car_id")
    private String carId;
    private int distance;
    private long timestamp;

    public CarLocation() {
    }

    public CarLocation(String carId, int distance, long timestamp) {
        super();
        this.carId = carId;
        this.distance = distance;
        this.timestamp = timestamp;
    }

    public String getCarId() {
        return carId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getDistance() {
        return distance;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "CarLocation{" +
                "carId='" + carId + '\'' +
                ", distance=" + distance +
                ", timestamp=" + timestamp +
                '}';
    }
}

