package com.vmall.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class UserTest {
    @Test
    public void TestPassword() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);
        System.out.println( passwordEncoder.matches("123", encode));
    }
}
