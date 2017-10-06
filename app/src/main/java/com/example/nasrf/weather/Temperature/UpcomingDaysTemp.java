package com.example.nasrf.weather.Temperature;

/**
 * Created by nasrf on 5/10/2017.
 */

public class UpcomingDaysTemp  {
    private String day,date,condition,high,low;

    public UpcomingDaysTemp(String day, String date, String condition, String high, String low) {
        this.day = day;
        this.date = date;
        this.condition = condition;
        this.high = high;
        this.low = low;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }
}
