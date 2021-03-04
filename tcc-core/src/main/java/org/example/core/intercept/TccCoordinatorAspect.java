package org.example.core.intercept;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author ：min lee
 * @date ：Created in 2021/3/3 17:06
 * @description： 参与者拦截器
 * @modified By：
 * @version: 0.1
 * @package： org.example.core.intercept
 */

@Component
@Aspect
@Order(2)
public class TccCoordinatorAspect {
}
