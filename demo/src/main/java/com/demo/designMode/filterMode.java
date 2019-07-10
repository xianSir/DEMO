package com.demo.designMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xks
 * @date 2019-03-28
 */
public class filterMode {
    /**
     * 通过代码进行条件判断
    * */
    public static void main(String[] args) {
        List<Employee> persons = new ArrayList<Employee>();
        persons.add(new Employee("Tom", "Male", "YES"));
        persons.add(new Employee("Jack", "Male", "NO"));
        persons.add(new Employee("Jane", "Female", "NO"));
        persons.add(new Employee("Diana", "Female", "YES"));
        persons.add(new Employee("Mike", "Male", "NO"));
        persons.add(new Employee("Bob", "Male", "YES"));

        Criteria male =   new CriteriaMale();
        Criteria female = new CriteriaMale();
        Criteria retire = new CriteriaRetire();
        Criteria retireMale = new AndCriteria(retire, male);
        Criteria retireOrFemale = new OrCriteria(retire, female);

        System.out.println("Males: ");
        printPersons(male.meetCriteria(persons));

        System.out.println("Females: ");
        printPersons(female.meetCriteria(persons));

        System.out.println("Retire Males: ");
        printPersons(retireMale.meetCriteria(persons));

        System.out.println("Retire Or Females: ");
        printPersons(retireOrFemale.meetCriteria(persons));
    }

    public static void printPersons(List<Employee> persons) {
        for (Employee person : persons) {
            System.out.println(person);
        }
    }

    static class Employee {
        private String name;
        private String gender;
        private String retireStatus;
        public Employee() {}
        public Employee(String name, String gender, String r) {
            this.name = name;
            this.gender = gender;
            this.retireStatus = r;
        }

        public String getName() {
            return name;
        }

        public String getGender() {
            return gender;
        }

        public String getRetireStatus() {
            return retireStatus;
        }

        @Override
        public String toString() {
            return "Employee [name=" + name + ", gender=" + gender
                    + ", retireStatus=" + retireStatus + "]";
        }
    }

    interface Criteria {
        public List<Employee> meetCriteria(List<Employee> persons);
    }

    static class CriteriaMale implements Criteria {

        @Override
        public List<Employee> meetCriteria(List<Employee> persons) {
            List<Employee> malePersons = new ArrayList<Employee>();
            for (Employee person : persons) {
                if (person.getGender().equalsIgnoreCase("MALE")) {
                    malePersons.add(person);
                }
            }
            return malePersons;
        }
    }

    class CriteriaFemale implements Criteria {

        @Override
        public List<Employee> meetCriteria(List<Employee> persons) {
            List<Employee> femalePersons = new ArrayList<Employee>();
            for (Employee person : persons) {
                if (person.getGender().equalsIgnoreCase("FEMALE")) {
                    femalePersons.add(person);
                }
            }
            return femalePersons;
        }
    }

    static class CriteriaRetire implements Criteria {

        @Override
        public List<Employee> meetCriteria(List<Employee> persons) {
            List<Employee> singlePersons = new ArrayList<Employee>();
            for (Employee person : persons) {
                if (person.getRetireStatus().equalsIgnoreCase("YES")) {
                    singlePersons.add(person);
                }
            }
            return singlePersons;
        }
    }

    static class AndCriteria implements Criteria {

        private Criteria criteria;
        private Criteria otherCriteria;

        public AndCriteria(Criteria criteria, Criteria otherCriteria) {
            this.criteria = criteria;
            this.otherCriteria = otherCriteria;
        }

        @Override
        public List<Employee> meetCriteria(List<Employee> persons) {
            List<Employee> firstCriteriaPersons = criteria.meetCriteria(persons);
            return otherCriteria.meetCriteria(firstCriteriaPersons);
        }
    }

    static class OrCriteria implements Criteria {

        private Criteria criteria;
        private Criteria otherCriteria;

        public OrCriteria(Criteria criteria, Criteria otherCriteria) {
            this.criteria = criteria;
            this.otherCriteria = otherCriteria;
        }

        @Override
        public List<Employee> meetCriteria(List<Employee> persons) {
            List<Employee> firstCriteriaItems = criteria.meetCriteria(persons);
            List<Employee> otherCriteriaItems = otherCriteria.meetCriteria(persons);

            for (Employee person : otherCriteriaItems) {
                if (!firstCriteriaItems.contains(person)) {
                    firstCriteriaItems.add(person);
                }
            }
            return firstCriteriaItems;
        }
    }

}
