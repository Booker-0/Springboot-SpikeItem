package com.lyy;

import com.lyy.dao.UserDOMapper;
import com.lyy.dataobject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Hello world!
 *
 */



@SpringBootApplication(scanBasePackages = {"com.lyy"})
@RestController
@MapperScan("com.lyy.dao")
public class App {
    @Resource
    private UserDOMapper userDOMapper;
    @RequestMapping("/")
        public String home(){

        UserDO userDO = userDOMapper.selectByPrimaryKey(40);

        if(userDO==null)
        {
            return "用户对象不存在";

        }
        else {
            return userDO.getName();
        }
        }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}