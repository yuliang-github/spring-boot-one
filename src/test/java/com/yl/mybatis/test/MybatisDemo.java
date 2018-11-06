package com.yl.mybatis.test;

import com.yl.common.bean.UserBasicBean;
import com.yl.common.mapper.UserBasicBeanMapper;
import com.yl.springboot.config.MyBatisConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Alex
 * @since 2018/11/5 09:58
 */
public class MybatisDemo {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyBatisConfig.class);

        UserBasicBeanMapper mapper = context.getBean(UserBasicBeanMapper.class);

        UserBasicBean user = mapper.get(1);

        System.err.println(user);

        System.err.println("--------------------------");

        UserBasicBean userBasicBean = new UserBasicBean();
        userBasicBean.setId(4);
        userBasicBean.setName("miss");
        System.err.println(mapper.del(userBasicBean));

        System.err.println("--------------------------");
        userBasicBean.setId(3);
        System.err.println(mapper.select(userBasicBean));



    }

}
