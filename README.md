目前负责的会员体系是通过Spring Cloud实现微服务架构，整体架构设计如下：

![default](https://user-images.githubusercontent.com/5570216/42261628-6af2e90e-7f9b-11e8-87b0-8b887c9a7f6a.png)

业务系统彼此不直接交互，只负责编排基础服务；基础服务提供单一的服务能力，供业务系统调用，也可直接对第三方业务系统开放使用，此次分享只针对业务系统做简单介绍。

# 业务
整个体系被划分成**主数据服务**（[CMDS SERVICE](https://github.com/kaybinwong/running-spring-boot-app-on-k8s/tree/master/mircoservices/cmds-service)）、**账号服务**（[PASSPORT SERVICE](https://github.com/kaybinwong/running-spring-boot-app-on-k8s/tree/master/mircoservices/passport-service)）等多个核心微服务。每个微服务都是围绕业务能力组织的可独立部署的应用程序，拥有独立的数据库并使用同步的[REST API](http://www.restapitutorial.com/)实现微服务与微服务之间的通信。

**主数据服务**（[CMDS SERVICE](https://github.com/kaybinwong/running-spring-boot-app-on-k8s/tree/master/mircoservices/cmds-service)）包含客户的所有数据信息，比如房产、家庭成员、沟通记录等。

方法	| 路径	| 描述	| 用户验证	|
------------- | ------------------------- | ------------- |:-------------:|
GET	| /customers/{mobile}	| 获取特定客户数据	|  | 	
GET	| /customers/	| 新增客户	|  | 	
GET	| /properties/{custId}	| 获取特定客户的房产数据	|  | 

**账户服务**（[PASSPORT SERVICE](https://github.com/kaybinwong/running-spring-boot-app-on-k8s/tree/master/mircoservices/passport-service)）包含仅包含客户的基本信息，负责用户注册、登录、token下发等。

方法	| 路径	| 描述	| 用户验证	|
------------- | ------------------------- | ------------- |:-------------:|
GET	| /accounts/{mobile}	| 获取特定账号数据	|  | 	
POST	| /accounts/	| 新增用户	| × | ×

# 组件
基础服务设施中用到了Spring Cloud Config、Netflix Eureka、Netflix Hystrix、Netflix Zuul、Netflix Ribbon、Netflix Feign等组件，这些都是Spring Cloud分布式开发中最核心的组件。
- Config Server负责集中管理所有服务的配置信息，这些配置信息包括队列、端口等参数；
- Service Registry（Euraka）注册中心，通过三节点互相注册达到高可用，账户服务等通过注册中心（Eureka）实现互相发现；
- API Gateway（Zuul）提供对外统一的服务网关，首先从注册中心（Eureka）处获取相应服务，再根据服务调用各个服务的真实业务逻辑；
- Open Tracer（Zipkin）负责全链路跟踪；
- 认证机制通过Auth service实现，提供基本认证服务；
- 账户服务通过远程客户端（Feign）调用主数据服务，通过Ribbon实现负载均衡，并在调用过程中增加了断路器（Hystrix）的功能；
- 主数据服务负责冗余所有（to B or to C）系统的数据信息；

Spring Cloud Config、Eureka、Ribbon、Hystrix、Feign以及Turbine均为标准组件，与业务之间没有强关系，不涉及到业务代码，仅需简单配置即可工作。
