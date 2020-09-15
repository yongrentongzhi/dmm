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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
