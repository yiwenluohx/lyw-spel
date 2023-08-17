package com.study.lywspel.interceptor;

import com.study.lywspel.annotation.SpelGetParm;
import com.study.lywspel.util.SpelUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author luohx
 * @version 1.0.0
 * @date: 2023/8/17 上午9:54
 * @menu
 */
@Component
@Aspect
public class SpelGetParmAop {
    private final static Logger log = LoggerFactory.getLogger(SpelGetParmAop.class);

    @PostConstruct
    public void init() {
        log.info("SpelGetParm init ......");
    }

    /**
     * 拦截加了SpelGetParm注解的方法请求
     *
     * @param joinPoint
     * @param spelGetParm
     * @return
     * @throws Throwable
     */
    @Around("@annotation(spelGetParm)")
    public Object beforeInvoce(ProceedingJoinPoint joinPoint, SpelGetParm spelGetParm) throws Throwable {
        Object result = null;
        // 方法名
        String methodName = joinPoint.getSignature().getName();
        //获取动态参数
        String parm = SpelUtil.generateKeyBySpEL(spelGetParm.param(), joinPoint);
        log.info("spel获取动态aop参数: {}", parm);
        try {
            log.info("执行目标方法: {} ==>>开始......", methodName);
            result = joinPoint.proceed();
            log.info("执行目标方法: {} ==>>结束......", methodName);
            // 返回通知
            log.info("目标方法 " + methodName + " 执行结果 " + result);
        } finally {

        }
        // 后置通知
        log.info("目标方法 " + methodName + " 结束");
        return result;
    }
}
