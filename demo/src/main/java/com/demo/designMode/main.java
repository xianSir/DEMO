package com.demo.designMode;

public class main {
    public static void main(String[] args) {
        /**适配器模式
        * 通过一个接口来创建不同对象
        * */
        /**桥接模式
        * 桥:一个接口
        * 接:通过一个第三方类连接两个不同的对象
        */
         /**
         * 装饰模式:
         * 通过接口或继承对同一个方法进行加工
         * 从而使该对象增加其他功能
         *
         *
         */
        /**外观模式
         * 对多个对象进行封装 使之对外同个同一个对象进不同工作
         *
         * */

        /**代理模式
         * 通俗来讲  就是一个对象流封装这另一个对象那
         *      对外显示创建的对象代理封装的对象进行工作
         * */
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
    public void print(int radius, int x, int y) {
        System.out.println("Color: " + radius +", x: " +x+", "+ y +"]");
    }
}
class BlackPrinter implements Printer {
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
