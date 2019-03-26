package app.Entities;


import app.annotations.Column;

import java.sql.Timestamp;

public class Transaction {
    @Column ("AccNo")
    private String accNo;
    @Column ("Receiver")
    private String receiver;
    @Column ("TransAmount")
    private double amount;
    @Column ("Date")
    private Timestamp date;
    @Column ("Balance")
    private double balance;

    public String getAccNo() {return accNo;}

    public String getReceiver() { return receiver; }

    public double getAmount() { return amount; }

    public String getDateAsString(){
        return String.valueOf(date);
    }

    public double getNewBalance() {
        return balance;
    }
}
