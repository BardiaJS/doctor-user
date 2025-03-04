package com.example.doctore_user;

public class Doctor {
    private int id;
    private String category;
    private String firstName;
    private String lastName;

    public Doctor(int id, String firstName , String lastName , String category) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.category = category;


    }

    public int getId() { return id; }
    public String getCategory() { return category; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }


}
