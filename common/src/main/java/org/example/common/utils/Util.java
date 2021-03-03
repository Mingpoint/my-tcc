package org.example.common.utils;

import org.example.common.rsp.HttpResult;
import org.example.common.rsp.RspCode;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/26 15:19
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.common.utils
 */
public class Util {
    public static <T> boolean checkResponse (HttpResult<T> response) {
        boolean flag = true;
        if (response == null) {
            flag = false;
        }
        if (!response.getCode().equals(RspCode.SUCCESS_CODE)) {
            flag = false;
        }
        return flag;
    }
}
