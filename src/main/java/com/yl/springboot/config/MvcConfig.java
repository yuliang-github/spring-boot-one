package com.yl.springboot.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex
 * @since 2018/8/28 14:15
 */
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 使用FastJson消息转换器
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        // 解决中文乱码
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);

        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.PrettyFormat);
        converters.add(fastConverter);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        HandlerInterceptor interceptor = new HandlerInterceptor() {
            /**
             * 在请求之前拦截
             */
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                System.err.println("请求之前url:" + request.getRequestURI());
                return true;
            }

            /**
             * 在请求之后,视图渲染之前拦截
             */
            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                System.err.println("请求之后url:" + request.getRequestURI());
            }

            /**
             * 在请求全部完成之后拦截
             */
            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
                System.err.println("请求完成url:" + request.getRequestURI());
            }
        };
        registry.addInterceptor(interceptor).addPathPatterns("/*");
    }
}
