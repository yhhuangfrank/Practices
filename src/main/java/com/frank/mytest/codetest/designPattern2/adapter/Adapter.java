package com.frank.mytest.codetest.designPattern2.adapter;

public class Adapter {
    public static void main(String[] args) {
        // Example 1
        SquareHole squareHole = new SquareHole(5);

        Square square = new Square(5);
        System.out.println(squareHole.canFit(square));            // true

        Circle circle = new Circle(5);
        CircleToSquareAdapter circleAdapter = new CircleToSquareAdapter(circle);
        System.out.println(squareHole.canFit(circleAdapter));     // false

        // Example 2
        squareHole = new SquareHole(5);

        square = new Square(6);
        System.out.println(squareHole.canFit(square));            // false

        circle = new Circle(2);
        circleAdapter = new CircleToSquareAdapter(circle);
        System.out.println(squareHole.canFit(circleAdapter));     // true

    }
}


class SquareHole {
    private double sideLength;

    public SquareHole(double sideLength) {
        this.sideLength = sideLength;
    }

    public boolean canFit(Square square) {
        return this.sideLength >= square.getSideLength();
    }

    public boolean canFit(CircleToSquareAdapter adapter) {
        return this.sideLength >= adapter.getSideLength();
    }

}

class Square {
    private double sideLength;

    public Square() {
    }

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    public double getSideLength() {
        return this.sideLength;
    }
}

class Circle {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return this.radius;
    }
}

class CircleToSquareAdapter extends Square {

    private Circle circle;

    public CircleToSquareAdapter(Circle circle) {
        // Write your code here
        this.circle = circle;
    }

    @Override
    public double getSideLength() {
        // Write your code here
        return this.circle.getRadius() * 2;
    }
}
