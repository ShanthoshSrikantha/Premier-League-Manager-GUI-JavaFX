import java.io.*;
import java.util.*;

public class RandomDate implements Serializable {
    private int date;
    private int month;
    private int year = 2020;

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        if ((date > 28) || (date < 0)) {
            System.out.println("Invalid Month!!!");
            throw new IllegalArgumentException();
        }
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if ((month > 12) || (month < 0)) {
            throw new IllegalArgumentException();
        }
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public RandomDate() {
        this.date = getRandomNumber(1, 28);
        this.month = getRandomNumber(1, 12);
        this.year = 2020;
    }

    public RandomDate(int date, int month, int year) {
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public RandomDate(int date, int month) {
        this.date = date;
        this.month = month;
    }

    public static int getRandomNumber(int lower, int upper) {
        Random rand = new Random();
        int randomNum = rand.nextInt((upper - lower) + lower);
        return randomNum;
    }

    @Override
    public String toString() {
        return date + "/" + month + "/" + year;
    }


}
