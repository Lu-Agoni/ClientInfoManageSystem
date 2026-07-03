package com.javaee.clientinfomanagesystem.service;
import com.javaee.clientinfomanagesystem.po.contact;
import java.util.List;

public interface contactService {
    List<contact> findContacts();
    contact findContact(int coid);
    int addContact(contact contact);
    int deleteContact(int coid);
    int updateContact(contact contact);
    List<contact> findContactByKey(String key);
}