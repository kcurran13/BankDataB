package app.Entities;


import app.annotations.Column;

public class Transaction {
    @Column ("AccNo")
    private String accNo;
    @Column ("Receiver")
    private String receiver;
    @Column ("TransAmount")
    private double amount;
    @Column ("Date")
    private String date;
    @Column ("Balance")
    private double balance;

    public String getAccNo() {return accNo;}

    public String getReceiver() { return receiver; }

    public double getAmount() { return amount; }

    public String getDateAsString(){
        return date;
    }

    public double getNewBalance() {
        return balance;
    }
}
