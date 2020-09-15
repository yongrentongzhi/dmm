package com.dmm.common.pojo;

import java.io.Serializable;

public class StatusCode implements Serializable {
    //响应状态相关
    public static final Integer OK=2000;
    public static final Integer ERROR = 2001;//失败
    public static final Integer LOGIN_ERROR = 2002;//用户名或密码错误
    public static final Integer ACCESS_ERROR = 2003;//权限不足
    public static final Integer REMOTE_ERROR = 2004;//远程调用失败
    public static final Integer REPEAT_ERROR = 2005;//重复操作
    public static final Integer NOTFOUND_ERROR = 2006;//没有对应的抢购数据
    //Spu商品状态相关
    public static final  Integer Spu_on_sale=1000;
    public static final  Integer Spu_off_sale=1001;
    public static final  Integer Spu_on_check=1003;

    //Sku商品状态相关
    public static final  Integer Sku_on_sale=1000;
    public static final  Integer Sku_off_sale=1001;
    public static final  Integer Sku_on_check=1003;
}
