package com.yl.springboot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alex
 * @since 2018/8/28 14:06
 * @des 主配置类
 */
@Configuration
@ComponentScans(value = {
        @ComponentScan(value = "com.yl",useDefaultFilters = false,includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {RestController.class})
        })
})
public class MainConfig {



}
