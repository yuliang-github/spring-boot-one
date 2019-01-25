package com.yl.springboot.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.yl.common.demo.CustomerGetMapping;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
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
        fastMediaTypes.add(MediaType.valueOf("text/html;charset=UTF-8"));
        fastConverter.setSupportedMediaTypes(fastMediaTypes);

        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.PrettyFormat);
        converters.add(fastConverter);
    }

    /**
     * 使用springmvc拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        HandlerInterceptor interceptor = new HandlerInterceptor() {
            /**
             * 在请求之前拦截
             */
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                System.err.println("请求之前url:" + request.getRequestURI());
                HandlerMethod handlerMethod = (HandlerMethod)handler;
                Method method = handlerMethod.getMethod();
                CustomerGetMapping customerGetMapping = AnnotationUtils.findAnnotation(method, CustomerGetMapping.class);
                if(customerGetMapping != null && customerGetMapping.needSession()){
                    System.err.println("检查会话,Url:" + request.getRequestURI());
                }
                //if(request.getRequestURL().toString().contains("getMsg")){
                    // 告诉浏览器使用UTF-8解码
                    // response.setHeader("Content-type", "text/html;charset=UTF-8");
                    // response.setCharacterEncoding("UTF-8");
                    // response.getWriter().write("没有权限访问!");
                    // 请求转发
                    // request.getRequestDispatcher("/index").forward(request, response);
                    // 重定向
                    // response.sendRedirect("/spring-boot/index");
                    // return false;
                //}
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
        /**
         * 此处的UrlParttern是在controller里面定义的路径,而不是从contextPath开始
         */
        registry.addInterceptor(interceptor).addPathPatterns("/");
    }

    /**
     * 自定义过滤器
     */
    @Bean
    public Filter customerFilter(){
        Filter customerFilter = new Filter() {
            @Override
            public void init(FilterConfig filterConfig) {
            }

            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                HttpServletRequest request = (HttpServletRequest)servletRequest;
                System.err.println("自定义过滤器,URL:" + request.getRequestURI());
                filterChain.doFilter(servletRequest, servletResponse);
            }

            @Override
            public void destroy() {

            }
        };
        return customerFilter;
    }

    /**
     * 注册过滤器
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(Filter customerFilter){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(customerFilter);
        // "/*表示拦截所有,包括静态文件"
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    // 配置静态资源映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/static-web-inf/**").addResourceLocations("/WEB-INF/static/");
    }

    // 配置视图解析器
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/jsp/", ".jsp");
    }
}
