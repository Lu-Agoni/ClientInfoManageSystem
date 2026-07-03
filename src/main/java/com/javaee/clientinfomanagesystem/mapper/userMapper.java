package com.javaee.clientinfomanagesystem.mapper;
import com.javaee.clientinfomanagesystem.po.user;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface userMapper {
    user login(String username,String password);
    int register(user user);
    List<user> findUsers();
    user findUserByUserId(int uid);
    user findByUsername(String username);
    int addUser(user user);
    int updateUser(user user);
    int deleteUserByUserId(int uid);
    int updatePassword(int uid, String newPassword);
    List<user> findUserByKey(String key);
    int deleteUserRole(int uid);
    int addUserRole(int uid, int rid);
}