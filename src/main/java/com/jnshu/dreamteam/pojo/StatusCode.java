package com.jnshu.dreamteam.pojo;

public enum  StatusCode {


    SUCCESS(200,"操作成功"),
    FAILURE(201,"操作失败"),
    DATA_IS_NULL(203,"数据不能为null"),
    SAVE_FAILD(204,"保存失败"),
    NOT_PERMISSIONS(205,"角色没有模块权限"),
    ID_NOT_NULL(206,"id不能为null");

    private int code;
    private String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
