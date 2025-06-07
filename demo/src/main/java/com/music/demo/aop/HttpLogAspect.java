package com.music.demo.aop;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.*;

@Aspect
@Component
public class HttpLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(HttpLogAspect.class);

    @Around("execution(* com.music.demo.controller..*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes != null ? attributes.getRequest() : null;

        // 构建日志对象
        HttpLog httpLog = new HttpLog();
        if (request != null) {
            httpLog.setIp(request.getRemoteAddr());
            httpLog.setUrl(request.getRequestURL().toString());
            httpLog.setMethod(request.getMethod());
            httpLog.setHeaders(getHeaders(request));
            httpLog.setTimestamp(LocalDateTime.now());
        }

        long startTime = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();

            httpLog.setTimeTaken(System.currentTimeMillis() - startTime);
            httpLog.setResponseBody(result);

            // 记录日志
            logger.info("HTTP请求日志: {}", formatHttpLog(httpLog));

            return result;
        } catch (Exception e) {
            logger.error("请求处理异常", e);
            throw e;
        }
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> headerMap = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            headerMap.put(key, request.getHeader(key));
        }
        return headerMap;
    }

    private String formatHttpLog(HttpLog httpLog) {
        return String.format(
                "IP: %s | URL: %s | Method: %s | Headers: %s | TimeTaken: %dms | Timestamp: %s",
                httpLog.getIp(),
                httpLog.getUrl(),
                httpLog.getMethod(),
                httpLog.getHeaders(),
                httpLog.getTimeTaken(),
                httpLog.getTimestamp()
        );
    }
}
