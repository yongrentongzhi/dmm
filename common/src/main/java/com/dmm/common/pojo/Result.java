package com.dmm.common.pojo;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private  Integer status;
    private  Boolean flag;
    private  String message;
    private T data;

    public Result(Integer status, Boolean flag, String message, T data) {
        this.status = status;
        this.flag = flag;
        this.message = message;
        this.data =  data;
    }

    public Result(Integer status, Boolean flag, String message) {
        this.status = status;
        this.flag = flag;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", flag=" + flag +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
