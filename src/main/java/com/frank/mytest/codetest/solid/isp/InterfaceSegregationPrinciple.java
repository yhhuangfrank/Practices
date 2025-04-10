package com.frank.mytest.codetest.solid.isp;

public class InterfaceSegregationPrinciple {

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.myCar.move();
        driver.myCar.startEngine();

        Mechanic mechanic = new Mechanic();
        mechanic.clientCar.openEngineerMode();
        mechanic.clientCar.repair();
    }

    interface DailyUsage {
        void startEngine();
        void move();
    }

    interface RepairUsage {
        void openEngineerMode();
        void repair();
    }

    static class Car implements DailyUsage, RepairUsage {

        @Override
        public void startEngine() {

        }

        @Override
        public void move() {

        }

        @Override
        public void openEngineerMode() {

        }

        @Override
        public void repair() {

        }
    }

     static class Driver {
        DailyUsage myCar = new Car();
    }

    static class Mechanic {
        RepairUsage clientCar = new Car();
    }
}
