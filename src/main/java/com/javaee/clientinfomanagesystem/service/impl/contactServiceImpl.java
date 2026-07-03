package com.javaee.clientinfomanagesystem.service.impl;
import com.javaee.clientinfomanagesystem.mapper.contactMapper;
import com.javaee.clientinfomanagesystem.po.contact;
import com.javaee.clientinfomanagesystem.service.contactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class contactServiceImpl implements contactService {
    @Autowired
    private contactMapper contactMapper;

    @Override
    public List<contact> findContacts(){
        return contactMapper.findContacts();
    }

    @Override
    public contact findContact(int coid){
        return contactMapper.findContact(coid);
    }

    @Override
    public int addContact(contact contact){
        return contactMapper.addContact(contact);
    }

    @Override
    public int updateContact(contact contact){
        return contactMapper.updateContact(contact);
    }

    @Override
    public int deleteContact(int coid){
        return contactMapper.deleteContact(coid);
    }

    @Override
    public List<contact> findContactByKey(String key) {
        return contactMapper.findContactByKey(key);
    }
}