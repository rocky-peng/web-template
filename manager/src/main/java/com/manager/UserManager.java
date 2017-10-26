package com.manager;

import com.commons.db.MasterDB;
import com.dao.UserDao;
import com.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author pengqingsong
 * @date 26/10/2017
 * @desc
 */
@Component
public class UserManager {

    @Autowired
    private UserDao userDao;

    public long addUser(String userName) {
        return userDao.addUser(userName);
    }

    @MasterDB  //这个注解告诉程序这个方法要再主库上执行，写操作一般在主库上执行，读操作一般在从库上执行
    public User getUser(long uid) {
        return userDao.getUser(uid);
    }

}
