package com.mj.config;

import com.mj.service.UserService;
import org.apache.dubbo.config.*;
import org.springframework.context.annotation.Bean;

//@Configuration
public class MyDubboConfig {
//    @Autowired
//    UserService userService;

    /**
     * 应用名
     * @return
     */
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("user-service-provider");
        return applicationConfig;
    }

    /**
     * 注册中心
     * @return
     */
    @Bean
    public RegistryConfig registryConfig(){
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("127.0.0.1:2181");
        return registryConfig;
    }


    /**
     * 指定通信规则（通信协议?通信端口）
     * @return
     */
    @Bean
    public ProtocolConfig protocolConfig(){
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20880);
        return  protocolConfig;
    }


    /**
     * 配置需要暴露的服务
     * @param userService
     * @return
     */
    @Bean
    public ServiceConfig<UserService> userServiceConfig(UserService userService){
        ServiceConfig<UserService> userServiceConfig = new ServiceConfig<>();
        userServiceConfig.setInterface(UserService.class);
        userServiceConfig.setRef(userService);
//        userServiceConfig.setVersion("1.0.0");

        //配置每一个method的信息
        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("getUserAddressList");
        methodConfig.setTimeout(1000);

//        userServiceConfig.setMethods(Arrays.asList(methodConfig));

//        ProviderConfig
//        MonitorConfig

        return  userServiceConfig;

    }


    @Bean
    public MonitorConfig monitorConfig(){
        MonitorConfig monitorConfig = new MonitorConfig();
        monitorConfig.setProtocol("registry");
        return monitorConfig;
    }
}
