package com.javaee.clientinfomanagesystem.po;
import lombok.Data;

@Data
public class contact {
    private int coid;
    private int cid;
    private String coname;
    private String position;
    private String cophone;
    private String coemail;
    private int isMain;
    private String clientName;
}
