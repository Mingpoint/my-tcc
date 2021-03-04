package org.example.core.intercept;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @author ：min lee
 * @date ：Created in 2021/3/3 17:05
 * @description： 协调者拦截器
 * @modified By：
 * @version: 0.1
 * @package： org.example.core.intercept
 */
@Component
@Aspect
@Order(1)
@Slf4j
public class TccTransactionAspect {
    @Autowired
    private TccTransactionIntercept tccTransactionIntercept;
    @Pointcut("@annotation(org.example.core.annotation.TccAnnotation)")
    public void tccMethod(){
    }
    @Around("tccMethod()")
    public Object AroundTransactionMethod (ProceedingJoinPoint pjp) {
        log.debug("TccTransactionAspect start {}",pjp.getSignature().getName());
        return tccTransactionIntercept.intercept(pjp);
    }
}
