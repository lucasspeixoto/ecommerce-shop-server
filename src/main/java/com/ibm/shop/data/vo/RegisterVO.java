package com.ibm.shop.data.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class RegisterVO {

    @NotEmpty(message = "Name should not be null or empty")
    @Size(min = 3, message = "Name body must be minimum 3 characters")
    private String name;

    @NotEmpty(message = "Username should not be null or empty")
    @Size(min = 3, message = "Username body must be minimum 3 characters")
    private String username;

    @NotEmpty(message = "Email should not be null or empty")
    @Email(message = "Invalid Email format")
    private String email;

    @NotEmpty(message = "Password should not be null or empty")
    @Size(min = 3, message = "Password body must be minimum 3 characters")
    private String password;

    public RegisterVO() {
    }

    public RegisterVO(String name, String username, String email, String password) {        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisterVO that)) return false;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getUsername(), getEmail(), getPassword());
    }
}
