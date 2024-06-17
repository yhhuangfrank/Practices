package com.frank.mytest.codetest.designPattern2;

public class Factory {

    public static void main(String[] args) {
        VehicleFactory carFactory = new CarFactory();
        VehicleFactory truckFactory = new TruckFactory();
        VehicleFactory bikeFactory = new BikeFactory();

        Vehicle myCar = carFactory.createVehicle();
        Vehicle myTruck = truckFactory.createVehicle();
        Vehicle myBike = bikeFactory.createVehicle();

        System.out.println(myCar.getType());   // "Car"
        System.out.println(myTruck.getType()); // "Truck"
        System.out.println(myBike.getType());  // "Bike"
    }

}

interface Vehicle {
    String getType();
}

class Car implements Vehicle {
    @Override
    public String getType() {
        return "Car";
    }
}

class Bike implements Vehicle {
    @Override
    public String getType() {
        return "Bike";
    }
}

class Truck implements Vehicle {
    @Override
    public String getType() {
        return "Truck";
    }
}

abstract class VehicleFactory {
    abstract Vehicle createVehicle();
}

class CarFactory extends VehicleFactory {
    @Override
    Vehicle createVehicle() {
        return new Car();
    }
}

class BikeFactory extends VehicleFactory {
    @Override
    Vehicle createVehicle() {
        return new Bike();
    }
}

class TruckFactory extends VehicleFactory {
    @Override
    Vehicle createVehicle() {
        return new Truck();
    }
}
