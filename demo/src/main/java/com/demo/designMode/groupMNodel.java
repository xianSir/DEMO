package com.demo.designMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xks
 * @date 2019-03-28
 */
public class groupMNodel {
    /**组合模式
    组合模式是结构型模式，因为它创建了一组对象的树结构。
    组合模式将一组对象视为单个对象。
    组合模式使用一个类来表示树结构。
    在组合模式中，我们创建一个包含自己对象的类的组。
    */
    public static void main(String[] args) {
        Employee CEO = new Employee("John","CEO");

        Employee headSales = new Employee("Rob","Sales");

        Employee headMarketing = new Employee("Mike","Marketing");

        Employee programmer1 = new Employee("Lili","Programmer");
        Employee programmer2 = new Employee("Bob","Programmer");

        Employee tester1 = new Employee("Jack","Tester");
        Employee tester2 = new Employee("Tom","Tester");

        CEO.add(headSales);
        CEO.add(headMarketing);

        headSales.add(tester1);
        headSales.add(tester2);

        headMarketing.add(programmer1);
        headMarketing.add(programmer2);

        //print all employees of the organization
        System.out.println(CEO);
        for (Employee headEmployee : CEO.getSubordinates()) {
            System.out.println(headEmployee);
            for (Employee employee : headEmployee.getSubordinates()) {
                System.out.println(employee);
            }
        }

    }

    static class Employee {
        private String name;
        private String title;
        private List<Employee> subordinates;

        public Employee(String name,String title) {
            this.name = name;
            this.title = title;
            subordinates = new ArrayList<Employee>();
        }

        public void add(Employee e) {
            subordinates.add(e);
        }

        public void remove(Employee e) {
            subordinates.remove(e);
        }

        public List<Employee> getSubordinates(){
            return subordinates;
        }

        public String toString(){
            return "Employee :[ Name : "+ name
                    +", dept : "+ title +subordinates +" ]";
        }
    }

}
