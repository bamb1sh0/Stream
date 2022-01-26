package com.company.itStep;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(fillOrder(1, 10)));
        System.out.println(calcSum(10));
        System.out.println(Arrays.toString(fillRandom(10, 11)));
        printEven(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        printEven(fillRandom(10, 10));
        System.out.println(Arrays.toString(squares(2)));
        System.out.println(Arrays.toString(powers2(10)));
        System.out.println(Arrays.toString(getDivisible()));
        System.out.println(countDivisible());
        printFirst20();
        System.out.println(Arrays.toString(calcPrimes(10)));
        System.out.println(maxPrimeFactor(25));

    }

    //Сгенерироваь массив последовательных натуральных чисел в заданном диапазоне
    public static int[] fillOrder(int begin, int end) {
        return IntStream.rangeClosed(begin, end)
                .toArray();
    }

    //Получить сумму первых n чисел
    public static int calcSum(int count) {
        int sum = IntStream.rangeClosed(1, count).sum();
        return sum;
    }

    //Сгенерироваь массив случайных натуральных чисел чисел в заданном диапазоне
    public static int[] fillRandom(int count, int bound) {
        Random random = new Random();
        return IntStream.generate(() -> random.nextInt(bound) + 1)
                // .distinct()    //не выводит повторяющиеся числа
                .limit(count)
                .toArray();
    }

    //Вывести только четные числа
    public static void printEven(int[] arr) {
        String s = Arrays.stream(arr)
                .filter(n -> n % 2 == 0)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(","));
    }

    //Вывести квадраты первых n натуральных чисел
    public static int[] squares(int count) {
        return IntStream.rangeClosed(1, count).map(n -> n * n).toArray();
    }

    //Степени числа 2
    public static int[] powers2(int count) {
        return IntStream.iterate(1, n -> n * 2)
                .limit(count)
                .toArray();
    }

    //Вывести все числа от 1 до 1000, которые делятся на 3, 5, и 11
    public static int[] getDivisible() {
        return IntStream.rangeClosed(1, 1000)
                .filter(n -> n % 3 == 0 || n % 5 == 0 || n % 11 == 0)
                .toArray();
    }

    //количество чисел
    public static long countDivisible() {
        return IntStream.rangeClosed(1, 1000)
                .filter(n -> n % 3 == 0 || n % 5 == 0 || n % 11 == 0)
                .count();
    }

    //Первое число, сумма цифр которого равна 20
    public static void printFirst20() {
        Stream.iterate(new int[]{1, 1}, x -> new int[]{++x[0], x[1] = sum(Integer.toString(x[0]).chars().toArray())})
                //  .filter(x -> x[1]==20)
                .dropWhile(x -> x[1] != 20)
                .limit(3)
                .forEach(x -> System.out.println(x[0]));
    }

    public static int sum(int[] arr) {
        int result = 0;
        for (int a : arr)
            result += a - '0';
        return result;
    }

    //Вывести n первых простых чисел
    public static boolean isPrime(int number) {
        return IntStream.rangeClosed(2, (int) Math.sqrt(number))
                .allMatch(n -> number % n != 0);
    }

    public static int[] calcPrimes(int count) {
        return IntStream.iterate(1, x -> x + 1)
                .filter(x -> isPrime(x))
                .limit(count)
                .toArray();
    }

    //Максимальный простой делитель числа
    public static int maxPrimeFactor(int number) {
        return IntStream.rangeClosed(2, number/2)
                .filter(n -> number % n == 0)
                .filter(n -> isPrime(n))
                .max()
                .getAsInt();
    }
}
