package org.example.common.rsp;

import lombok.Data;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/23 17:47
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.common
 */
@Data
public class HttpResult <T> {
    private String code;
    private String message;
    private T data;

    public HttpResult(String code,String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }
    public HttpResult(String code,String message,T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public HttpResult(T data) {
        this.code = RspCode.SUCCESS_CODE;
        this.message = RspCode.SUCCESS_MSG;
        this.data = data;
    }
}
