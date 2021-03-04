package org.example.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：min lee
 * @date ：Created in 2021/3/3 16:03
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.core.annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TccAnnotation {
    public String rollBackMethod() default "";
    public String confirmMethod() default "";

}
