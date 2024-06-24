package com.frank.mytest.codetest.designPattern2;

public class Facade {
    public static void main(String[] args) {
        DriveThruFacade driveThru = new DriveThruFacade();

        Food dineInFood = driveThru.takeOrder("Burger and Fries", /* takeOut= */ false);
        System.out.println(dineInFood.getFood()); // "Burger and Fries"

        Food takeOutFood = driveThru.takeOrder("Pizza", /* takeOut= */ true);
        System.out.println(takeOutFood.getFood()); // "Pizza in a bag"
    }
}

class Order {
    private String contents;
    private boolean takeOut;

    public Order(String contents, boolean takeOut) {
        this.contents = contents;
        this.takeOut = takeOut;
    }

    public String getOrder() {
        return contents;
    }

    public boolean isTakeOut() {
        return takeOut;
    }
}

class Cashier {
    public Order takeOrder(String contents, boolean takeOut) {
        return new Order(contents, takeOut);
    }
}

class Food {
    private String contents;

    public Food(String order) {
        this.contents = order;
    }

    public String getFood() {
        return contents;
    }
}

class Chef {
    public Food prepareFood(Order order) {
        return new Food(order.getOrder());
    }
}

class PackagedFood extends Food {
    public PackagedFood(Food food) {
        super(food.getFood() + " in a bag");
    }
}

class KitchenStaff {
    public PackagedFood packageOrder(Food food) {
        return new PackagedFood(food);
    }
}

class DriveThruFacade {
    private Cashier cashier = new Cashier();
    private Chef chef = new Chef();
    private KitchenStaff kitchenStaff = new KitchenStaff();

    public Food takeOrder(String orderContents, boolean takeOut) {
        Order order = cashier.takeOrder(orderContents, takeOut);
        Food food = chef.prepareFood(order);
        if (order.isTakeOut()) {
            return kitchenStaff.packageOrder(food);
        }
        return food;
    }
}
