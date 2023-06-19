package com.ibm.shop.data.vo;

import java.util.Objects;

public class LoginDTO {

    private String usernameOrEmail;

    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String usernameOrEmail, String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
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
        if (!(o instanceof LoginDTO loginDTO)) return false;
        return Objects.equals(getUsernameOrEmail(), loginDTO.getUsernameOrEmail()) && Objects.equals(getPassword(), loginDTO.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsernameOrEmail(), getPassword());
    }
}
