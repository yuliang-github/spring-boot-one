package com.yl.common.handler.exception;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.yl.common.response.RestResult;
import com.yl.exception.common.MvcException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex
 * @since 2019/2/19 15:10
 */
//@Component("exceptionHandler")
public class DefaultExceptionHandler implements HandlerExceptionResolver {

    /**
     * 自定义全局异常处理器
     *  1.可利用FastJsonJsonView来返回json数据
     *  2.直接只用response来直接写入
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv = new ModelAndView();
        this.dealByFastJason(mv, ex);
        //this.dealByResponse(ex,response);
        return mv;
    }

    private void dealByFastJason(ModelAndView mv,Exception ex){
        FastJsonJsonView view = new FastJsonJsonView();
        Map<String,Object> retMap = new HashMap<>();
        if(ex instanceof MvcException){
            retMap.put("message", ex.getMessage());
            retMap.put("subCode", 22);
            retMap.put("code", 2);
        }else {
            retMap.put("message", "服务错误,请联系平台客服");
            retMap.put("subCode", 11);
            retMap.put("code", 1);
        }
        view.setAttributesMap(retMap);
        mv.setView(view);
    }

    private void dealByResponse(Exception ex,HttpServletResponse response){
        response.setStatus(HttpStatus.OK.value()); //设置状态码
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
        response.setCharacterEncoding("UTF-8"); //避免乱码
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        try {
            response.getWriter().write(new RestResult(1,11,ex.getMessage()).toJson());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
