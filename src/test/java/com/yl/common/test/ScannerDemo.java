package com.yl.common.test;

import com.yl.common.demo.CustomerGetMapping;
import com.yl.common.utils.PackageSanner;
import jodd.io.findfile.ClassScanner;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alex
 * @since 2018/8/28 15:27
 */
public class ScannerDemo {

    public static void main(String[] args){

        PackageSanner scanner = new PackageSanner();

        Set<BeanDefinition> beans = scanner.doScan("com.yl.common", new PackageSanner.FilterProvider() {
            @Override
            public Set<TypeFilter> addFilter() {
                Set<TypeFilter> filters = new HashSet<>();
                filters.add(new AnnotationTypeFilter(CustomerGetMapping.class));
                return filters;
            }

            @Override
            public Set<TypeFilter> subFilter() {
                return null;
            }
        });

        for (BeanDefinition bean:beans){
            System.err.println("clazz:"+bean.getBeanClassName());
        }

    }

}
