package com.ibm.shop.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGeneratorEncoder {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String user = passwordEncoder.encode("lucas");
        String admin = passwordEncoder.encode("admin");
        System.out.println("My hash for user " + user);
        System.out.println("My hash for admin " + admin);
    }
}
