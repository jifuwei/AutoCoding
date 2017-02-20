package com.autocoding.ac.foundation.aop;

import com.alibaba.fastjson.JSON;
import com.autocoding.ac.foundation.annotation.ACLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * aop拦截：自定义注解类@ACLog
 * Created by JFW on 2017/1/19.
 */
@Aspect
@Component
public class ACLogAOP {

    private static final Logger LOGGER = LoggerFactory.getLogger(ACLogAOP.class);

    @Pointcut("@annotation(com.autocoding.ac.foundation.annotation.ACLog)")
    private void log() {

    }

    /**
     * 前置通知
     *
     * @param joinPoint
     */
    @Before(value = "log()")
    public void beforeAdvice(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //记录请求的IP
        String ip = request.getRemoteAddr();
        try {
            LOGGER.info("\n请求方法:{}.{}()\n方法描述:{}\n请求IP:{}", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), getControllerMethodDescription(joinPoint), ip);

            //TODO:1.构造日志对象；2.保存到数据库中; 3.上线运行需有需求关闭相应的日志处理
        } catch (Exception e) {
            //记录本地异常日志
            LOGGER.error("发现错误:", e);
        }
    }

    /**
     * 手动控制调用核心业务逻辑，以及调用前和调用后的处理
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "log()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] objects = proceedingJoinPoint.getArgs();
        String name = proceedingJoinPoint.getSignature().getName();
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();

        LOGGER.info("\n请求类:{}, 请求方法:{}\n入参:<{}>, 出参:<{}>\n执行时长:{}", proceedingJoinPoint.getTarget().getClass().getName(), name, JSON.toJSONString(objects), JSON.toJSONString(result), System.currentTimeMillis() - startTime);

        return result;
    }

    /**
     * 核心业务逻辑调用异常退出后，执行此Advice，处理错误信息
     * <p/>
     * 注意：执行顺序在Around Advice之后
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "log()", throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
        LOGGER.error("\n入参:<{}>\n错误信息:", JSON.toJSONString(joinPoint.getArgs()), ex);
    }

    /**
     * 获取注解中对方法的描述信息
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(ACLog.class).description();
                    break;
                }
            }
        }
        return description;
    }
}
