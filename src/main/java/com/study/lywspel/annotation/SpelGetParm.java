package com.study.lywspel.annotation;

import java.lang.annotation.*;

/**
 * 定义aop，方便spel取值
 *
 * @author luohx
 * @version 1.0.0
 * @date: 2023/8/17 上午9:51
 * @menu 定义aop，方便spel取值
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SpelGetParm {
    String param() default "";
}
