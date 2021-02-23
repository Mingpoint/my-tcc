package org.example.common.exceptions;

import lombok.Data;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/23 17:15
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.common.exceptions
 */
@Data
public class TccException extends RuntimeException {
    private String code;
    private String message;
    public TccException (String code,String message) {
        super(new RuntimeException());
        this.code = code;
        this.message = message;
    }
}
