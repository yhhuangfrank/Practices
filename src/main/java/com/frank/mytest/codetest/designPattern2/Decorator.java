package com.frank.mytest.codetest.designPattern2;

public class Decorator {
    public static void main(String[] args) {

        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getCost());    // 1.1

        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getCost());    // 1.6

        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getCost());    // 1.8

        coffee = new CreamDecorator(coffee);
        System.out.println(coffee.getCost());    // 2.5

    }
}

abstract class Coffee {
    public abstract double getCost();
}

class SimpleCoffee extends Coffee {
    @Override
    public double getCost() {
        return 1.1;
    }
}

abstract class CoffeeDecorator extends Coffee {
    protected Coffee decoratedCoffee;

    protected CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return 0.5 + super.getCost();
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return 0.2 + super.getCost();
    }

}

class CreamDecorator extends CoffeeDecorator {
    public CreamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return 0.7 + super.getCost();
    }
}

