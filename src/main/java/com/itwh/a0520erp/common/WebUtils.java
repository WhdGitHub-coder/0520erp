package com.itwh.a0520erp.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * @Author 翁煌
 * @Description:
 * @Version 1.0
 */
public class WebUtils {

    /*
    得到request
     */
    public static HttpServletRequest getRequest(){

        ServletRequestAttributes requestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        return request;
    }

    /*
    得到session
     */
    public static HttpSession getSession(){
        return getRequest().getSession();
    }
}
