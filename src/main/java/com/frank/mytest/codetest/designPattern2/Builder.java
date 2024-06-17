package com.frank.mytest.codetest.designPattern2;

public class Builder {
    public static void main(String[] args) {

        MealBuilder builder = new MealBuilder();
        Meal myMeal = builder.addCost(15.99)
                .addTakeOut(true)
                .addMainCourse("Burger")
                .addDrink("Coke")
                .build();

        System.out.println(myMeal.getCost());       // Cost: 15.99
        System.out.println(myMeal.getTakeOut());    // TakeOut: true
        System.out.println(myMeal.getMain());       // Main: "Burger"
        System.out.println(myMeal.getDrink());      // Drink: "Coke"

    }
}

class Meal {

    private double cost;
    private boolean takeOut;
    private String main;
    private String drink;

    double getCost() {
        return this.cost;
    }

    boolean getTakeOut() {
        return this.takeOut;
    }

    String getMain() {
        return this.main;
    }

    String getDrink() {
        return this.drink;
    }

    void setCost(double cost) {
        this.cost = cost;
    }

    void setTakeOut(boolean takeOut) {
        this.takeOut = takeOut;
    }

    void setMain(String main) {
        this.main = main;
    }

    void setDrink(String drink) {
        this.drink = drink;
    }
}

class MealBuilder {

    private Meal meal;

    public MealBuilder() {
        this.meal = new Meal();
    }

    public MealBuilder addCost(double cost) {
        meal.setCost(cost);
        return this;
    }

    public MealBuilder addTakeOut(boolean takeOut) {
        meal.setTakeOut(takeOut);
        return this;
    }

    public MealBuilder addMainCourse(String main) {
        meal.setMain(main);
        return this;
    }

    public MealBuilder addDrink(String drink) {
        meal.setDrink(drink);
        return this;
    }

    Meal build() {
        return this.meal;
    }
}

