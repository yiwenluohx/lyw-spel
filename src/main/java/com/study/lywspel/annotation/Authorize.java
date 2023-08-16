package com.study.lywspel.annotation;

import java.lang.annotation.*;

/**
 * 权限校验
 *
 * @author luohx
 * @version 1.0.0
 * @date: 2023/8/15 下午2:44
 * @menu 权限校验
 * @Target：注解的作用目标
 * @Retention：注解的生命周期
 * @Documented：注解是否应当被包含在 JavaDoc 文档中
 * @Inherited：是否允许子类继承该注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Authorize {
    enum Type {
        OWNER,
        BILL_DETAIL,
        BILL_ACTION,
        ONCE_AUTH,
        OTHER
    }

    /**
     * 鉴权类型
     *
     * @return
     */
    Type type() default Type.OWNER;

    String eid() default "";

    String eidExpress() default "";

    /**
     * 异常信息
     *
     * @return
     */
    String message() default "暂无权限操作,请联系管理员";
}
