package com.vmall.domain;

import lombok.Data;

@Data
public class OrderRes {
    private Long id;
    private String uName;
    private String gName;
    private Long gId;
    private Long uid;
}
