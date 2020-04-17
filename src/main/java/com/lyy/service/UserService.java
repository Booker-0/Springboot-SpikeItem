package com.lyy.service;

import com.lyy.error.BusinessException;
import com.lyy.service.model.UserModel;

public interface UserService {
    //通过用户id获取用户对象
    UserModel getUserById(Integer Id);
    //对用户的注册
    void register(UserModel userModel) throws BusinessException;
    //encrptPassword是用户加密后的密码
    UserModel validateLogin(String telphone, String encrptPassword) throws BusinessException;
}
