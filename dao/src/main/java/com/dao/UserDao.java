package com.dao;

import com.commons.db.BaseJdbcDao;
import com.commons.utils.MapUtils;
import com.dao.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * @author pengqingsong
 * @date 26/10/2017
 * @desc
 */
@Repository
public class UserDao extends BaseJdbcDao {

    public User getUser(long uid) {
        String sql = "select uid,user_name from user where uid = ?";
        Map<String, Object> map = selectOne(sql, uid);
        if (CollectionUtils.isEmpty(map)) {
            return null;
        }

        User user = new User();
        user.setUid(MapUtils.getLong(map, "uid"));
        user.setUserName(MapUtils.getString(map, "user_name"));

        // 上面三行代码可以用下面的代码代替，但需要有一定的条件
        // User user = MapToBeanUtils.convertMapToBean(map,User.class);

        return user;
    }

    public long addUser(String userName) {
        String sql = "insert into user(user_name) values (?);";
        return insertAndGetKey(sql, userName);
    }

}
