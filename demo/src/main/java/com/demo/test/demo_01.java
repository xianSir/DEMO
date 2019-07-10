package com.demo.test;

/**
 * @author xks
 * @date 2019-03-18
 */


public class demo_01 {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100,100, 10, new ColorPrinter());
        Shape blackCircle = new Circle(100,100, 10, new BlackPrinter());

        redCircle.draw();
        blackCircle.draw();
    }
}
interface Printer {
    public void print(int radius, int x, int y);
}
class ColorPrinter implements Printer {
    @Override
    public void print(int radius, int x, int y) {
        System.out.println("Color: " + radius +", x: " +x+", "+ y +"]");
    }
}
class BlackPrinter implements Printer {
    @Override
    public void print(int radius, int x, int y) {
        System.out.println("Black: " + radius +", x: " +x+", "+ y +"]");
    }
}
abstract class Shape {
    protected Printer print;
    protected Shape(Printer p){
        this.print = p;
    }
    public abstract void draw();
}
class Circle extends Shape {
    private int x, y, radius;

    public Circle(int x, int y, int radius, Printer draw) {
        super(draw);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void draw() {
        print.print(radius,x,y);
    }
}
