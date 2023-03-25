package com.vmall.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtTest {
    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void testjwt() {
        System.out.println(jwtUtil.getHeader());
        String token = jwtUtil.createToken("123");
        System.out.println(token);

    }
    @Test
    public void testparse() {
        System.out.println(jwtUtil.parseToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjMiLCJpYXQiOjE2Nzk2NjQzMDUsImV4cCI6MTY3OTY2NzkwNSwiaXNzIjoicGFueml5ZSJ9.rIL4o73ZnMFlRO2SvdHwX6yCbkNqY05MFE58FxB7Y4A"));
    }
}
