package org.user.proto;

import lombok.Data;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/24 17:33
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.user.proto
 */
@Data
public class RollBackAmountReq extends BaseAmount {
    private Long rollBackAmount;
    private String trackNo;
}
