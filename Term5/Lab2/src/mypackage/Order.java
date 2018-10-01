package mypackage;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Order implements Serializable{
    private String date;
    private String type;
    private double price;

    private int quarter;
    private int month;
    private int year;

    public Order(String date, String type, double price) {
        this.date = date;
        this.type = type;
        this.price = price;

        String[] arr = date.split("[.]");
        this.month = Integer.parseInt(arr[1]);
        this.year = Integer.parseInt(arr[2]);
        if(month>=1 && month <=3)
            this.quarter = 1;
        else if(month >= 4 && month <= 6)
            this.quarter = 2;
        else if(month >= 7 && month <= 9)
            this.quarter = 3;
        else this.quarter = 4;
    }

    public String getDate(){
        return date;
    }

    public String getType() {
        return type;
    }

    public int getQuarter() {
        return quarter;
    }

    public int getMonth() {
        return month;
    }

    public double getPrice() {
        return price;
    }

    public int getYear(){
        return year;
    }

    @Override
    public String toString() {
        return "date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", price(rub)=" + price;
    }

    private void countQuarter(){
        String[] arr = date.split(".");
    }
}
