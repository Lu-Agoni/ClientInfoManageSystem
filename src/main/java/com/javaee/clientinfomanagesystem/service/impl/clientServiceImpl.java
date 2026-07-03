package com.javaee.clientinfomanagesystem.service.impl;
import com.javaee.clientinfomanagesystem.mapper.clientMapper;
import com.javaee.clientinfomanagesystem.po.client;
import com.javaee.clientinfomanagesystem.service.clientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class clientServiceImpl implements clientService{
    @Autowired
    private clientMapper clientMapper;

    @Override
    public List<client> findClients() {
        return clientMapper.findClients();
    }

    @Override
    public client findClient(int cid) {
        return clientMapper.findClient(cid);
    }

    @Override
    public int addClient(client client){
        return clientMapper.addClient(client);
    }

    @Override
    public int deleteClient(int cid) {
        return clientMapper.deleteClient(cid);
    }

    @Override
    public int updateClient(client client){
        return clientMapper.updateClient(client);
    }

    @Override
    public List<client> findClientByKey(String key) {
        return clientMapper.findClientByKey(key);
    }
}
