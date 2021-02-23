package org.example.common.rsp;

import lombok.Data;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/23 17:50
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.common.rsp
 */
@Data
public class RspCode {
    public final static String SUCCESS_CODE = "success";
    public final static String SUCCESS_MSG = "成功";
    public final static String FAIL_CODE = "fail";
    public final static String FAIL_MSG = "失败";
}
