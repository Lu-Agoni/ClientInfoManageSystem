package com.javaee.clientinfomanagesystem.po;
import lombok.Data;

@Data
public class client {
    private int cid;
    private String cno;
    private String cname;
    private String cphone;
    private String caddress;
    private String industry;
    private String level;
    private String source;
    private int contactCount;
}
