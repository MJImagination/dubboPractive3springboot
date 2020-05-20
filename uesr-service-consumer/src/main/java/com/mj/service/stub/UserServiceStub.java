package com.mj.service.stub;

import com.mj.bean.UserAddress;
import com.mj.service.UserService;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 本地存根 一般放到api项目中去
 * http://dubbo.apache.org/zh-cn/docs/user/demos/local-stub.html
 */
public class UserServiceStub implements UserService {


    public final UserService userService;


    /**
     * 传入的是userservice的远程代理对象
     *
     * @param userService
     */
    public UserServiceStub(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        System.out.println("userServiceStub......");
        if (!StringUtils.isEmpty(userId)) {
            return userService.getUserAddressList(userId);
        }
        return null;
    }
}
