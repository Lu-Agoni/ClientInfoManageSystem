package com.javaee.clientinfomanagesystem.service;
import com.javaee.clientinfomanagesystem.po.opportunity;
import java.util.List;

public interface opportunityService {
    List<opportunity> findOpportunitys();
    opportunity findOpportunity(int opid);
    int addOpportunity(opportunity opportunity);
    int deleteOpportunity(int opid);
    int updateOpportunity(opportunity opportunity);
    List<opportunity> findOpportunityByKey(String key);
}