package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Flight {
    private String code;
    private String departure;
    private String arrival;
    private Date date;
    private String hour;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM");

    public Flight(String code, String departure, String arrival, String date, String hour) throws ParseException {
        this.code = code;
        this.departure = departure;
        this.arrival = arrival;
        this.date = DATE_FORMAT.parse(date);
        this.hour = hour;
    }

    // Getters
    public String getCode() { return code; }
    public String getDeparture() { return departure; }
    public String getArrival() { return arrival; }
    public Date getDate() { return date; }
    public String getHour() { return hour; }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %s",
                code, departure, arrival, DATE_FORMAT.format(date), hour);
    }
}