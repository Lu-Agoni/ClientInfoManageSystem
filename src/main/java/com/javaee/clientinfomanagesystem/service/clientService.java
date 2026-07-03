package com.javaee.clientinfomanagesystem.service;
import com.javaee.clientinfomanagesystem.po.client;
import java.util.List;

public interface clientService {
    List<client> findClients();
    client findClient(int cid);
    int addClient(client client);
    int deleteClient(int cid);
    int updateClient(client client);
    List<client> findClientByKey(String key);
}
