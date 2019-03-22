package app.Entities;


import app.annotations.Column;

public class Transaction {
    @Column
    private long id;
    @Column
    private String receiver;
    @Column
    private float amount;
    @Column
    private java.sql.Timestamp date;

    public String getReceiver() { return receiver; }

    public float getAmount() { return amount; }

    /*public ZonedDateTime getDate() {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.of("Europe/Berlin"));
    }
    public String getDateAsString(){
        return getDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace('T', ' ');
    }*/
    public String getDateAsString() {
        return "2018-03-22";
    }
}
