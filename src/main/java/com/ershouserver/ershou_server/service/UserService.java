package com.ershouserver.ershou_server.service;

import com.ershouserver.ershou_server.entity.User;
import com.ershouserver.ershou_server.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

}
