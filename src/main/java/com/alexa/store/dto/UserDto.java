package com.alexa.store.dto;

public class UserDto {
    private int userId;
    private String firstName;
    private String lastname;
    private String email;

    public UserDto() {
    }

    public UserDto(int userId, String firstName, String lastname, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastname = lastname;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
