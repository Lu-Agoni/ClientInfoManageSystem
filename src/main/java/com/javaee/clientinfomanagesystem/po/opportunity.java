package com.javaee.clientinfomanagesystem.po;
import lombok.Data;

@Data
public class opportunity {
    private int opid;
    private int cid;
    private String title;
    private java.math.BigDecimal amount;
    private int probability;
    private String status;
    private String clientName;
}
