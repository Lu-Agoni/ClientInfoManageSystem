package com.javaee.clientinfomanagesystem.service.impl;
import com.javaee.clientinfomanagesystem.mapper.userMapper;
import com.javaee.clientinfomanagesystem.po.user;
import com.javaee.clientinfomanagesystem.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class userServiceImpl implements userService {
    @Autowired
    private userMapper userMapper;

    @Override
    public user login(String username,String password) {
        return userMapper.login(username,password);
    }

    @Override
    public int register(user user) {
        return userMapper.register(user);
    }

    @Override
    public List<user> findUsers() {
        return userMapper.findUsers();
    }

    @Override
    public user findUserByUserId(int uid) {
        return userMapper.findUserByUserId(uid);
    }

    @Override
    public user findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public int addUser(user user){
        return userMapper.addUser(user);
    }

    @Override
    public int updateUser(user user){
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUserByUserId(int uid) {
        return userMapper.deleteUserByUserId(uid);
    }

    @Override
    public int updatePassword(int uid, String newPassword) {
        return userMapper.updatePassword(uid,newPassword);
    }

    @Override
    public List<user> findUserByKey(String key) {
        return userMapper.findUserByKey(key);
    }

    @Override
    public int deleteUserRole(int uid) {
        return userMapper.deleteUserRole(uid);
    }

    @Override
    public int addUserRole(int uid, int rid) {
        return userMapper.addUserRole(uid, rid);
    }
}
