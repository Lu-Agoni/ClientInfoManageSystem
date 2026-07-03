package com.javaee.clientinfomanagesystem.mapper;
import com.javaee.clientinfomanagesystem.po.contact;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface contactMapper {
    List<contact> findContacts();
    contact findContact(int coid);
    int addContact(contact contact);
    int deleteContact(int coid);
    int updateContact(contact contact);
    List<contact> findContactByKey(String key);
}