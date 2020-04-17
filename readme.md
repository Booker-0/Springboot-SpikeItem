## Springboot-秒杀活动项目



#### 环境要求

java:java8

ide:idea

Maven  

DBMS:mysql 8.0.17（可自己在pom.xml修改为自己使用的mysql版本）

springboot:2.2.4



#### 项目运行方式：

1.在mysql workbench或者其他mysql gui程序中运行miaosha.sql先建立数据库环境

2.在idea导入该项目，导入相关依赖，然后在mybatis-generator.xml中修改相关sql连接配置，修改application.properties中的端口配置。

3.运行app，直接在浏览器打开resources目录下的getotp.html进行测试。



#### 运行截图：

参考screenshot内容

注册

登录

创建商品

商品列表

商品详情

商品下单

#### **电商秒杀应用简介**

> * 商品列表页获取秒杀商品列表
>
> * 进入商品详情页获取秒杀商品详情
>
> * 秒杀开始后进入下单确认页下单并支付成功
>
>   

#### 待补充功能：

如何发现容量问题

如何使得系统水平扩展

实现多用户，多商品，多交易

使用redis

