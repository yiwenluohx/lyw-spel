package com.study.lywspel.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author luohx
 * @version 1.0.0
 * @date: 2023/8/15 下午3:57
 * @menu
 */
@Component
@Aspect //@Aspect：声明该类为一个注解类
public class WebLogAspect {
    private final static Logger log = LoggerFactory.getLogger(WebLogAspect.class);

    //线程变量的使用，当前切面类中使用线程变量存储变量
    private ThreadLocal<Long> startTime = new ThreadLocal<>();
    //拦截的method
    private static final Set<String> METHOD_SET = Sets.newHashSet("POST", "PUT", "PATCH", "DELETE");

    /**
     * @Pointcut：定义一个切点，后面跟随一个表达式，表达式可以定义为切某个注解，也可以切某个 package 下的方法；
     * <p>
     * 此处的切点是注解的方式，也可以用包名的方式达到相同的效果（同时获取多个切点）
     * '@Pointcut("execution(* com.study.lywspel.*.*(..))")'
     * @Pointcut("execution(public * com.test.aaa..*.*(..))")
     */
    @Pointcut("@annotation(com.study.lywspel.annotation.Authorize)")
    public void cutWebLog() {
    }

    @Before("cutWebLog()")
    public void doBefore(JoinPoint joinPoint) {
        //设置请求开始时间
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //打印请求日志
        this.printRequestLog(request, joinPoint);
    }

    @AfterReturning(returning = "response", pointcut = "cutWebLog()")
    public void doAfterReturning(Object response) {
        // 处理完请求，返回内容
        log.info("【RESPONSE】: " + JSON.toJSONString(response));
        log.info("【SPEND TIME】: " + (System.currentTimeMillis() - startTime.get()) + "ms");
    }

    /**
     * 打印请求日志
     *
     * @param request   请求
     * @param joinPoint 连接点
     */
    private void printRequestLog(HttpServletRequest request, JoinPoint joinPoint) {
        //获取请求方式
        String method = request.getMethod();
        //打印日志
        log.info("【full requestURL】:" + request.getRequestURL().toString());
        log.debug("【remoteHost】:" + request.getRemoteHost());
        log.info("【headers】:" + this.getHeadersInfo(request));
        log.info("【parameters】:" + this.getParam(request.getParameterMap()));
        //判断method是否满足定义拦截的请求类型
        if (METHOD_SET.contains(method)) {
            log.info("【" + request.getMethod() + "Params】:" + getRequestParam(joinPoint, request));
        }
        //打印拦截的方法全路径
        log.info("【classMethod】:" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }

    /**
     * 从header里面获取headerNames
     *
     * @param request 请求
     * @return {@link JSONObject}
     */
    private JSONObject getHeadersInfo(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        Enumeration<String> headerNames = request.getHeaderNames();
        //循环遍历headerNames，组装map
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            jsonObject.put(key, value);
        }
        return jsonObject;
    }

    /**
     * 得到参数
     *
     * @param map 地图
     * @return {@link String}
     */
    private String getParam(Map<String, String[]> map) {
        StringBuilder str = new StringBuilder();
        for (String key : map.keySet()) {
            if (!str.toString().equals(""))
                str.append("&");
            str.append(key + "= " + String.join(",", map.get(key)));
        }
        return str.toString();
    }

    /**
     * 满足 POST, PUT, PATCH, DELETE时，打印对应body入参
     *
     * @param joinPoint 连接点
     * @param request   请求
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    private Map<String, Object> getRequestParam(JoinPoint joinPoint, HttpServletRequest request) {
        Map<String, Object> paramMap = new LinkedHashMap<>();
        //获取连接点（Joint Point）的签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        //遍历paramNames
        for (int i = 0; i < parameterNames.length; i++) {
            if (args[i] instanceof HttpServletRequest) {
                Enumeration<String> params = request.getParameterNames();
                while (params.hasMoreElements()) {
                    String key = params.nextElement();
                    paramMap.put(key, request.getParameter(key));
                }
            } else if (args[i] instanceof ExtendedServletRequestDataBinder) {
                //不返回任何值
            }else {
                paramMap.put(parameterNames[i], JSON.toJSONString(args[i]));
            }
        }
        return paramMap;
    }
}
