# Distributed-Microservice-Mall
采用SpringCloud+Mybatis为主要技术栈，作为B2C模式商城的开发脚手架，实现了分布式商城的主要功能，涉及到了elasticsearch、Redis、Oauth2、JWT令牌、分布式文件存储等内容。
# 技术清单: 


|说明  | 技术    |  版本    |
|:---------------:|:----------------:| :----------------:| 
|容器及MVC框架	   |Spring Boot	       |2.3.3|
|ORM框架|	MyBatis-plus |	Spring Boot对应版本|
|服务注册中心	|Eureka|	Hoxton.SR8 对应版本|
|服务调度、负载均衡	|OpenFeign|	Hoxton.SR8 对应版本
|服务降级、服务熔断	|Hystrix|	Hoxton.SR8 对应版本
|分布式事务	|Seata|	Cloud Alibaba 2.2.1对应
|网关限流	|Gateway	|Hoxton.SR8 对应版本
|认证、授权|	Spring Security OAuth2	|Hoxton.SR8 对应版本
|搜索引擎	|elasticsearch	|7.9
|缓存服务	|redis|	6.5
|分布式文件存储	|FastDFS|	6.06
|数据库	|MySQL|	8.0

# 业务流程
## 1.认证、授权
###  1.1. 认证
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200914003525632.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDIyODM3Ng==,size_16,color_FFFFFF,t_70#pic_center)
1、用户请求认证服务完成认证。
2、认证服务下发用户身份令牌，拥有身份令牌表示身份合法。
3、用户携带令牌请求资源服务，请求资源服务必先经过网关。
4、网关校验用户身份令牌的合法，不合法表示用户没有登录，如果合法则放行继续访问。
5、资源服务获取令牌，根据令牌完成授权。
6、资源服务完成授权则响应资源信息。
###  1.2. 公钥私钥授权![在这里插入图片描述](https://img-blog.csdnimg.cn/20200914003654997.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDIyODM3Ng==,size_16,color_FFFFFF,t_70#pic_center)
1、客户端请求认证服务申请令牌
2、认证服务生成令牌认证服务采用非对称加密算法，使用私钥生成令牌。
3、客户端携带令牌访问资源服务客户端在Http header 中添加： Authorization：Bearer 令牌。
4、资源服务请求认证服务校验令牌的有效性资源服务接收到令牌，使用公钥校验令牌的合法性。
5、令牌有效，资源服务向客户端响应资源信息
###  1.3. 用户登录
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200914004200832.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDIyODM3Ng==,size_16,color_FFFFFF,t_70#pic_center)
1、用户登录，请求认证服务 
2、认证服务认证通过，生成jwt令牌，将jwt令牌及相关信息写入cookie 
3、用户访问资源页面，带着cookie到网关 
4、网关从cookie获取token，如果存在token，则校验token合法性，如果不合法则拒绝访问，否则放行 
5、用户退出，请求认证服务，删除cookie中的token 

## 2.商品管理
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200914005300243.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDIyODM3Ng==,size_16,color_FFFFFF,t_70#pic_center)
一套完整的商品信息获取流程如上。该流程被设计给后台人员使用，数据来源为MySQL，因此所有的查询的语句均为单表查询，由业务层进行代码补偿，使用ID进行查询，避开join语句。`之所以避开join语句，是为了防止在大数据量的情况下，锁定时间过长。`

分布式ID生成策略采用百度开源的`UidGenerator`，核心算法是改进版的雪花算法，项目中使用的是`CachedUidGenerator`。
##  3.商品搜索
用户进行商品搜索时，数据来源来自elasticsearch，因此应提前将数据从mysql中导入elasticsearch中。elasticsearch会为每个字段生成倒排索引，因此搜索性能极佳，但是不能进行频繁更改。项目提供了elasticsearch实现的搜索功能和导入功能，但是没有提供修改删除等操作，这部分基于实际业务再做考虑。
##  4.添加购物车
项目未设计与支付宝沙箱或微笑支付的接入，因此默认加入购物车即完成下单，立刻开始分布式事务。项目采用的是Alibaba开源的`seata`完成分布式事务管理，采用的`AT`模式，保证数据的强一致性。值得注意的是，这里的数据强一致性仅指mySQL的数据一致，elasticsearch中的库存量数据同步问题，暂未考虑好，留待解决。

# 待解决问题
1、elasticsearch数据与mysql数据同步问题
2、设计秒杀活动时，诸多细节没考虑好，假定思路为：
      用户下单，主程将订单信息写入redis，另开一个线程从redis中读取数据存入mq中，订单微服务读取mq中数据，刷新到mysql中。
3、增加MongoDB作为文档数据源，保存评论。
