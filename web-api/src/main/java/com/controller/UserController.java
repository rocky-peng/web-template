package com.controller;

import com.commons.web.GeneralResponse;
import com.dao.entity.User;
import com.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pengqingsong
 * @date 26/10/2017
 * @desc
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 如果在浏览器访问 /api/user/get?uid=5 就会执行这个方法。
     * <p>
     * 前面的/api 是在 web.xml文件中配置的
     * <p>
     * 中间的/user 就是当前这个类上面的配置的
     * <p>
     * 最后的/get 就是在这个方法上面配置的
     *
     * @param userId 就是在链接里传递的参数
     * @return
     */
    @RequestMapping("/get")
    public GeneralResponse<User> getUser(@RequestParam("uid") long userId) {

        //这里只是对入参进行一些简单的校验
        if (userId <= 0) {
            return GeneralResponse.failedResponse("uid:无效的用户ID");
        }

        User user = userService.getUser(userId);
        return GeneralResponse.successResponse(user);
    }

    /**
     * 如果在浏览器访问 /api/user/add?userName=first_java 就会执行这个方法。
     * <p>
     * 前面的/api 是在 web.xml文件中配置的
     * <p>
     * 中间的/user 就是当前这个类上面的配置的
     * <p>
     * 最后的/add 就是在这个方法上面配置的
     *
     * @param userName 就是在链接里传递的参数
     * @return
     */
    @RequestMapping("/add")
    public GeneralResponse<Long> addUser(@RequestParam("userName") String userName) {

        //这里只是对入参进行一些简单的校验
        if (StringUtils.isBlank(userName)) {
            return GeneralResponse.failedResponse("userName:无效的用户ID");
        }

        long uid = userService.addUser(userName);
        return GeneralResponse.successResponse(uid);
    }


}
