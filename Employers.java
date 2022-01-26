package com.company.itStep;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Employers {
    public static void main(String[] args) {
        ArrayList<Employer> employers = read();
        System.out.println("-------------------------------");
        printAll(employers);
        System.out.println("-------------------------------");
        printPosition(employers);
        System.out.println("----------------------");
        maxSalary(employers);

    }

    public static ArrayList<Employer> read() {
        String fileName = "src\\com\\company\\itStep\\persons.txt";
        ArrayList<Employer> employers = null;
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            employers = (ArrayList<Employer>)
                    stream.map(s -> s.replaceAll("[\"]+|[\\s]+", ""))
                            .map(s -> s.split(","))
                            .map(s -> new Employer(s[0], s[1], Double.parseDouble(s[2]), Integer.parseInt(s[3]), s[4]))
                            //    .forEach(System.out::println);
                            .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employers;
    }

    public static void printAll(ArrayList<Employer> employers) {
        employers.stream().forEach(System.out::println);
    }

    public static void printPosition(ArrayList<Employer> employers) {
        employers.stream()
                .map(o -> o.getPosition())
                .distinct()
                .forEach(System.out::println);
    }

    public static void maxSalary(ArrayList<Employer> employers) {
        employers.stream()
                .map(o -> (double) (o.getSalary()))
                .max(Double::compareTo)
                .stream().sorted()
                .forEach(System.out::println);

    }
    public static void maxSalary2(ArrayList<Employer> employers){
        Comparator<Double> comparator = (a,b)->
                Double.compare(a,b);
        Double max = employers.stream()
                .map(o->(double)(o.getSalary()))
                .max(comparator).orElse((double) 0);
    }
  //  Comparator<Double> comparator = a,b -> Double.compare(a,b);

}

class Employer {
    String name;
    String surname;
    double salary;
    int age;
    String position;


    public Employer(String name, String surname, double salary, int age, String position) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.age = age;
        this.position = position;
    }

    public String toString() {
        return String.format(
                "[name: %s, surname: %s, salary: %.0f, age: %d, position: %s]", name, surname, salary, age, position
        );
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public double getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

}
