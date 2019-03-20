package app.Entities;


import app.annotations.Column;

public class User {
    @Column("id")
    private long id;
    @Column
    private String name;

    @Override
    public String toString(){
        return String.format("User: { id: %d, name: %s}", id, name);
    }
}
