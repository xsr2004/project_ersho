package com.ershouserver.ershou_server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ershouserver.ershou_server.entity.User;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
