package com.mj.service.impl;


import com.mj.bean.UserAddress;
import com.mj.service.OrderService;
import com.mj.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 1、将服务提供者注册到注册中心（暴露服务）
 * 1）、导入dubbo依赖（2.6.2）\操作zookeeper的客户端(curator)
 * 2）、配置服务提供者
 * <p>
 * 2、让服务消费者去注册中心订阅服务提供者的服务地址
 *
 * @author lfy
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Value("${serviceNum}")
    String serviceNum;

     /* 不用zookeeper方式，直连服务  配置文件要修改*/
//    @Reference(stub = "com.mj.service.stub.UserServiceStub",url = "127.0.0.1:20880")

    /*本地存根使用*/
    @Reference(stub = "com.mj.service.stub.UserServiceStub" ,loadbalance = "roundrobin")
      /*最基本的使用*/
//    @Reference()
    UserService userService;

    @HystrixCommand(fallbackMethod = "initOrderFall")
    @Override
    public List<UserAddress> initOrder(String userId) {
        // TODO Auto-generated method stub
        System.out.println("用户id：" + userId);
        System.out.println("服务:" + serviceNum );
        //1、查询用户的收货地址
        List<UserAddress> addressList = userService.getUserAddressList(userId);
        for (UserAddress userAddress : addressList) {
            System.out.println(userAddress.getUserAddress());
        }
        return addressList;
    }


    public List<UserAddress> initOrderFall(String userId) {
        return null;
    }


}
