package org.example.core.intercept;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.example.core.transaction.TransactionManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：min lee
 * @date ：Created in 2021/3/3 17:21
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.core.intercept
 */
@Component
@Slf4j
public class TccTransactionIntercept {
    @Autowired
    private TransactionManage transactionManage;
    public Object intercept (ProceedingJoinPoint pjp) {
        //1. 创建事务 try
        transactionManage.createTransaction();




        //2. 提交事务 commit
        //3. 更新事务状态 update
        return null;
    }
}
