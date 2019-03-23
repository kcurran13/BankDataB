package app.Entities;


import app.annotations.Column;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Transaction {
    @Column ("AccNo")
    private String accNo;
    @Column ("Receiver")
    private String receiver;
    @Column ("TransAmount")
    private double amount;
    @Column ("Date")
    private String date;
    @Column ( "Balance")
    private double newBalance;

    public String getAccNo() {return accNo;}

    public String getReceiver() { return receiver; }

    public double getAmount() { return amount; }

    /*public ZonedDateTime getDate() {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.of("Europe/Berlin"));
    }*/

    public String getDateAsString(){
        return date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
    }

    public double getNewBalance() {
        return newBalance;
    }
}
