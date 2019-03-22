package app.Entities;


import app.annotations.Column;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    @Column ("AccNo")
    private String accNo;
    @Column ("Receiver")
    private String receiver;
    @Column ("TransAmount")
    private double amount;
    @Column ("Date")
    private java.sql.Timestamp date;
    @Column ( "Balance")
    private double newBalance;

    public String getAccNo() {return accNo;}

    public String getReceiver() { return receiver; }

    public double getAmount() { return amount; }

    /*public ZonedDateTime getDate() {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.of("Europe/Berlin"));
    }
    public String getDateAsString(){
        return getDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace('T', ' ');
    }*/
    public String getDateAsString() {
        return "2018-03-22";
    }

    public double getNewBalance() {
        return newBalance;
    }
}
