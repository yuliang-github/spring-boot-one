package com.yl.common.utils;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.TypeFilter;

import java.util.Set;

/**
 * @author Alex
 * @since 2018/8/28 10:28
 * @des 自定义包扫描器
 */
public class PackageSanner {

    public Set<BeanDefinition> doScan(String pkg,FilterProvider filterProvider){
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        for (TypeFilter typeFilter:filterProvider.addFilter()){
            scanner.addIncludeFilter(typeFilter);
        }
        for (TypeFilter typeFilter:filterProvider.subFilter()){
            scanner.addExcludeFilter(typeFilter);
        }
        return scanner.findCandidateComponents(pkg);
    }

    /**
     * 自定义扫描过滤器
     */
    public interface FilterProvider {
        public Set<TypeFilter> addFilter();
        public Set<TypeFilter> subFilter();
    }

}
