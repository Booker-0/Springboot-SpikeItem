package com.lyy.service.impl;


import com.lyy.dao.UserDOMapper;
import com.lyy.dao.UserPasswordDOMapper;
import com.lyy.dataobject.UserDO;
import com.lyy.dataobject.UserPasswordDO;
import com.lyy.error.BusinessException;
import com.lyy.error.EmBusinessError;
import com.lyy.service.UserService;
import com.lyy.service.model.UserModel;


import com.lyy.validator.ValidationResult;
import com.lyy.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;
    @Autowired
    private ValidatorImpl validator;
    @Override
    public UserModel getUserById(Integer Id) {
        //调用usermapper获取对应的用户dataobject
        UserDO userDo = userDOMapper.selectByPrimaryKey(Id);
        if(userDo==null){
            return null;
        }
        //通过用户id获取对应的用户加密密码信息
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDo.getId());
        return convertFromDO(userDo,userPasswordDO);
    }

    @Override
    //事务
    @Transactional
    public void register(UserModel userModel) throws BusinessException {
        //判空操作
//        if(userModel==null) {
//            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
//        }
        //Apache lang包下的StringUtils
        //判断输入是否都不为空
//                if (StringUtils.isEmpty(userModel.getName())
//                || userModel.getGender() == null
//                || userModel.getAge() == null
//                || StringUtils.isEmpty(userModel.getTelphone())) {
//            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
//        }

        ValidationResult result = validator.validate(userModel);
        if (result.isHasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }
        //实现model到do的转换从而能存储到数据库中
        UserDO userDO = convertFromModel(userModel);
       try {
           userDOMapper.insertSelective(userDO);
       }//这个异常就是指出现了唯一索引的异常
       catch (DuplicateKeyException ex){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"手机号已经重复注册");
       }
        //需要在这里加以下内容以解决password userid为0问题
        userModel.setId(userDO.getId());
        UserPasswordDO userPasswordDO = convertPasswordFromModel(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);
        return;
    }

    @Override
    public UserModel validateLogin(String telphone, String encrptPassword) throws BusinessException {
        //通过用户手机获取用户信息

        UserDO userDO = userDOMapper.selectByTelphone(telphone);

        if (userDO == null) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel = convertFromDO(userDO, userPasswordDO);

        //比对用户信息内加密的密码是否和传输进来的密码相匹配
        if (!StringUtils.equals(encrptPassword, userModel.getEncrptPassword())) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }

        return userModel;
    }

    private UserDO convertFromModel(UserModel userModel){
        if (userModel==null)
            return null;
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel,userDO);
        return userDO;
    }

    private UserPasswordDO convertPasswordFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setEncrptPassword(userModel.getEncrptPassword());
        userPasswordDO.setUserId(userModel.getId());

        return userPasswordDO;
    }
    private UserModel convertFromDO(UserDO userDO, UserPasswordDO userPasswordDO){
       //判空操作
        if(userDO==null){
            return null;
        }
        UserModel userModel = new UserModel();
        //把userDO对应的属性copy到userModel类中
        BeanUtils.copyProperties(userDO,userModel);
        if(userPasswordDO!=null){
            userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        }

        return userModel;
    }
}
