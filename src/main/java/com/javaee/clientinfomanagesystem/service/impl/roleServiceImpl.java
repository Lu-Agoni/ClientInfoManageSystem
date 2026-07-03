package com.javaee.clientinfomanagesystem.service.impl;
import com.javaee.clientinfomanagesystem.po.role;
import com.javaee.clientinfomanagesystem.service.roleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.javaee.clientinfomanagesystem.mapper.roleMapper;
import java.util.List;

@Service
@Transactional
public class roleServiceImpl implements roleService {
    @Autowired
    private roleMapper roleMapper;
    @Override
    public List<role> findRoles() {
        return roleMapper.findRoles();
    }
}
