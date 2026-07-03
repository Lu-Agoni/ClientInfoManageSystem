package com.javaee.clientinfomanagesystem.service.impl;
import com.javaee.clientinfomanagesystem.mapper.opportunityMapper;
import com.javaee.clientinfomanagesystem.po.opportunity;
import com.javaee.clientinfomanagesystem.service.opportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class opportunityServiceImpl implements opportunityService {
    @Autowired
    private opportunityMapper opportunityMapper;

    @Override
    public List<opportunity> findOpportunitys(){
        List<opportunity> list = opportunityMapper.findOpportunitys();
        return list == null ? new ArrayList<>() : list;
    }

    @Override
    public opportunity findOpportunity(int opid){
        return opportunityMapper.findOpportunity(opid);
    }

    @Override
    public int addOpportunity(opportunity opportunity){
        return opportunityMapper.addOpportunity(opportunity);
    }

    @Override
    public int deleteOpportunity(int opid){
        return opportunityMapper.deleteOpportunity(opid);
    }

    @Override
    public int updateOpportunity(opportunity opportunity){
        return opportunityMapper.updateOpportunity(opportunity);
    }

    @Override
    public List<opportunity> findOpportunityByKey(String key) {
        List<opportunity> list = opportunityMapper.findOpportunityByKey(key);
        return list == null ? new ArrayList<>() : list;
    }
}