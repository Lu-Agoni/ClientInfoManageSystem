package com.javaee.clientinfomanagesystem.mapper;
import com.javaee.clientinfomanagesystem.po.opportunity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface opportunityMapper {
    List<opportunity> findOpportunitys();
    opportunity findOpportunity(int opid);
    int addOpportunity(opportunity opportunity);
    int deleteOpportunity(int opid);
    int updateOpportunity(opportunity opportunity);
    List<opportunity> findOpportunityByKey(String key);
}