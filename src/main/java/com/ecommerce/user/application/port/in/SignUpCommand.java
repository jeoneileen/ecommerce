package com.ecommerce.user.application.port.in;

public class SignUpCommand {

    private final String email;
    private final String password;
    private final String phoneNumber;
    private final String userName;

    public SignUpCommand(String email, String password, String phoneNumber, String userName) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserName() {
        return userName;
    }
}