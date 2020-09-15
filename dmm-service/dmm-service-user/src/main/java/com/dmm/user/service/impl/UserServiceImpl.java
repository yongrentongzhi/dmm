package com.dmm.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmm.common.pojo.user.User;
import com.dmm.user.dao.UserMapper;
import com.dmm.user.service.IUserService;

public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
