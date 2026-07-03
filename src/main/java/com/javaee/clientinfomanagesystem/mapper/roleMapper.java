package com.javaee.clientinfomanagesystem.mapper;
import com.javaee.clientinfomanagesystem.po.role;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface roleMapper {
    List<role> findRoles();
}
