package com.javaee.clientinfomanagesystem.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.javaee.clientinfomanagesystem.po.client;
import java.util.List;

@Mapper
public interface clientMapper {
    List<client> findClients();
    client findClient(int cid);
    int addClient(client client);
    int deleteClient(int cid);
    int updateClient(client client);
    List<client> findClientByKey(String key);
}
