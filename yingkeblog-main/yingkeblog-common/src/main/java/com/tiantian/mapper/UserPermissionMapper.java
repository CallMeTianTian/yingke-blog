package com.tiantian.mapper;

import com.tiantian.pojo.UserPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserPermissionMapper {
    UserPermission selectPermission(Integer id);

    int addPermissionById(Integer id,List<String> userPermissionList,List<String> roleList);
}
