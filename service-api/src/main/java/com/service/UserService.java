package com.service;

import com.dao.entity.User;

/**
 * @author pengqingsong
 * @date 26/10/2017
 * @desc
 */
public interface UserService {

    User getUser(long uid);

    long addUser(String userName);

}
