package com.service.impl;

import com.dao.entity.User;
import com.manager.UserManager;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pengqingsong
 * @date 26/10/2017
 * @desc
 */
@Repository
public class UserImpl implements UserService {

    private Map<Long, User> userCache = new HashMap<>();

    @Autowired  //这个注解是用来进行依赖注入的
    private UserManager userManager;

    @Override
    public User getUser(long uid) {
        //service层可以做一些缓存，中间件等处理，这里用map对象来模拟缓存
        User user = userCache.get(uid);

        //如果缓存里没有
        if (user == null) {
            //从数据库读取数据
            user = userManager.getUser(uid);

            //放入cache
            userCache.put(uid, user);
        }

        return user;
    }

    @Override
    public long addUser(String userName) {
        return userManager.addUser(userName);
    }
}
